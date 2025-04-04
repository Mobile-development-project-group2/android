package com.example.advancedandroidcourse.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedandroidcourse.model.teamDetails.TeamDetails
import com.example.advancedandroidcourse.model.teamDetails.TeamDetailsResponse
import com.example.advancedandroidcourse.repository.LeagueRepository
import com.example.advancedandroidcourse.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class TeamDetailsState{
    object Loading : TeamDetailsState()
    data class Success(val teamDetails: TeamDetails) : TeamDetailsState()
    data class Error(val message: String) : TeamDetailsState()
}


@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val _teamDetailsState = MutableStateFlow<TeamDetailsState>(TeamDetailsState.Loading)
    val teamDetailsState: StateFlow<TeamDetailsState> = _teamDetailsState

    fun fetchTeamDetails(teamId: Int) {
        viewModelScope.launch {
            try {
                Log.d("FETCH", "Fetching team details for teamId: $teamId")
                val response: TeamDetailsResponse = teamRepository.getTeamDetails(teamId)
                Log.d("RESPONSE", "Team details response: $response")

                // Check if the response is successful
                if (response.success) {
                    // Map the response to the TeamDetails UI model
                    val teamDetails = response.data

                    // Update state with success
                    _teamDetailsState.value = TeamDetailsState.Success(teamDetails)
                } else {
                    _teamDetailsState.value = TeamDetailsState.Error("Failed to fetch team details")
                }
            } catch (e: Exception) {
                Log.e("ERROR", "Error fetching team details", e)
                e.printStackTrace()
                _teamDetailsState.value = TeamDetailsState.Error("Error fetching team details: ${e.message}")
            }
        }
    }
}