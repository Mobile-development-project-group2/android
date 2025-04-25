package com.example.advancedandroidcourse.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedandroidcourse.model.matches.MatchesByDateUiState
import com.example.advancedandroidcourse.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class MatchesByDateViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MatchesByDateUiState>(MatchesByDateUiState.Loading)
    val uiState: StateFlow<MatchesByDateUiState> = _uiState

    fun fetchMatchesByDate(date: String = getCurrentDate()) {
        viewModelScope.launch {
            _uiState.value = MatchesByDateUiState.Loading
            try {
                val response = repository.getMatchesByDate(date)
                _uiState.value = MatchesByDateUiState.Success(response)
            } catch (e: Exception) {
                Log.e("MatchesByDateViewModel", "Error fetching matches: ${e.message}", e)
                val errorMessage = when {
                    e.message?.contains("Failed fetching league data") == true ->
                        "Unable to retrieve matches. Please try again later."
                    e.message?.contains("timeout") == true ->
                        "Connection timed out. Please check your internet connection."
                    e.message?.contains("Unable to resolve host") == true ->
                        "Network error. Please check your internet connection."
                    else -> e.message ?: "Unknown error occurred"
                }
                _uiState.value = MatchesByDateUiState.Error(errorMessage)
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}