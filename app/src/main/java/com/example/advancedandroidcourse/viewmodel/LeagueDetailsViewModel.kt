package com.example.advancedandroidcourse.viewmodel

import LeagueDetails
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedandroidcourse.model.TeamDetails
import com.example.advancedandroidcourse.repository.LeagueRepository
import com.example.advancedandroidcourse.ui.screens.League
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class LeagueDetailsState{
    object Loading: LeagueDetailsState()
    data class Success(val leagueDetails : LeagueDetails): LeagueDetailsState()
    object Error: LeagueDetailsState()
}
data class LeagueDetailsResponse(
    val success: Boolean,
    val data: LeagueDetails
)


@HiltViewModel
class LeagueDetailsViewModel @Inject constructor(
    private val leagueRepository: LeagueRepository
) : ViewModel() {

    private val _leagueDetailsState = MutableStateFlow<LeagueDetailsState>(LeagueDetailsState.Loading)
    val leagueDetailsState: StateFlow<LeagueDetailsState> = _leagueDetailsState

    fun fetchLeagueDetails(leagueCode: String) {
        viewModelScope.launch {
            try {
                Log.d("FETCH", "Fetching league details for $leagueCode")
                val response: LeagueDetailsResponse = leagueRepository.getLeagueDetails(leagueCode)
                Log.d("RESPONSE", "League details response: $response")
                // Check if the response is successful
                if (response.success) {
                    // Map the response to the LeagueDetails UI model
                    val leagueDetails = response.data

                    // Update state with success
                    _leagueDetailsState.value = LeagueDetailsState.Success(leagueDetails)
                } else {

                    _leagueDetailsState.value = LeagueDetailsState.Error
                }
            } catch (e: Exception) {
                Log.e("ERROR", "Error fetching league details", e)
                e.printStackTrace()
                _leagueDetailsState.value = LeagueDetailsState.Error
            }
        }
    }
}

