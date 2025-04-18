import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LiveScoreViewModel : ViewModel() {
    private val socketService = SocketService()

    val matchState: StateFlow<Match> = socketService.matchState
    val goals: StateFlow<List<GoalEvent>> = socketService.goals
    val isConnected: StateFlow<Boolean> = socketService.isConnected

    init {
        connectToServer()
    }

    private fun connectToServer() {
        viewModelScope.launch {
            // Replace with your server URL
            val serverUrl = "http://10.0.2.2:3000"  // For emulator
            // Use your machine's IP address for physical device, e.g., "http://192.168.1.100:3000"
            socketService.connect(serverUrl)
        }
    }

    override fun onCleared() {
        super.onCleared()
        socketService.disconnect()
    }
}