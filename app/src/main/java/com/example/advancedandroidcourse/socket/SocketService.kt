import android.util.Log
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject
import java.net.URISyntaxException

class SocketService {
    private var socket: Socket? = null
    private val gson = Gson()

    // StateFlows to observe in Compose
    private val _matchState = MutableStateFlow(Match())
    val matchState = _matchState.asStateFlow()

    private val _goals = MutableStateFlow<List<GoalEvent>>(emptyList())
    val goals = _goals.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    val isConnected = _isConnected.asStateFlow()

    fun connect(serverUrl: String) {
        try {
            val options = IO.Options()
            socket = IO.socket(serverUrl, options)

            socket?.on(Socket.EVENT_CONNECT) {
                Log.d(TAG, "Connected to server")
                _isConnected.value = true
            }

            socket?.on(Socket.EVENT_DISCONNECT) {
                Log.d(TAG, "Disconnected from server")
                _isConnected.value = false
            }

            socket?.on(Socket.EVENT_CONNECT_ERROR) { args ->
                Log.e(TAG, "Connection error: ${args[0]}")
                _isConnected.value = false
            }

            // Listen for match updates
            socket?.on("matchUpdate") { args ->
                try {
                    val matchJson = args[0] as JSONObject
                    val match = gson.fromJson(matchJson.toString(), Match::class.java)
                    Log.d(TAG, "Match update received: $match")
                    _matchState.value = match
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing match update", e)
                }
            }

            // Listen for goal events
            socket?.on("goalScored") { args ->
                try {
                    val eventJson = args[0] as JSONObject
                    val match = gson.fromJson(eventJson.getJSONObject("match").toString(), Match::class.java)
                    val goalEvent = gson.fromJson(eventJson.getJSONObject("goalEvent").toString(), GoalEvent::class.java)
                    Log.d(TAG, "Goal scored: $goalEvent")

                    // Update match state
                    _matchState.value = match

                    // Add goal to the list (at the beginning)
                    val currentGoals = _goals.value.toMutableList()
                    currentGoals.add(0, goalEvent)
                    _goals.value = currentGoals
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing goal event", e)
                }
            }

            socket?.connect()
        } catch (e: URISyntaxException) {
            Log.e(TAG, "Error connecting to server", e)
        }
    }

    fun disconnect() {
        socket?.disconnect()
        socket = null
        _isConnected.value = false
    }

    companion object {
        private const val TAG = "SocketService"
    }
}
data class Match(
    val id: String = "",
    val homeTeam: String = "",
    val awayTeam: String = "",
    val homeScore: Int = 0,
    val awayScore: Int = 0,
    val minute: Int = 0,
    val lastEvent: GoalEvent? = null
)

data class GoalEvent(
    val minute: Int,
    val team: String,
    val scorer: String
)