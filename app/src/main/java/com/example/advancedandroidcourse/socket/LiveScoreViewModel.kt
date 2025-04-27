import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.advancedandroidcourse.BuildConfig



class LiveScoreViewModel : ViewModel() {
    private val socketService = SocketService()

    val matchState: StateFlow<Match> = socketService.matchState
    val goals: StateFlow<List<GoalEvent>> = socketService.goals
    val isConnected: StateFlow<Boolean> = socketService.isConnected

    val baseUrl = BuildConfig.BASE_URL;


    init {
        connectToServer()
    }

    private fun connectToServer() {
        viewModelScope.launch {

            val serverUrl = baseUrl

            socketService.connect(serverUrl)
        }
    }

    override fun onCleared() {
        super.onCleared()
        socketService.disconnect()
    }
}