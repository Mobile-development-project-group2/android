package com.example.advancedandroidcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.advancedandroidcourse.ui.screens.LeaguesDetailsScreen
import com.example.advancedandroidcourse.ui.screens.LeaguesScreen
import com.example.advancedandroidcourse.ui.theme.AdvancedAndroidCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedAndroidCourseTheme {
                val navController = rememberNavController();
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(navController,Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hellosunojopa $name!",
        modifier = modifier
    )
}

@Composable
fun AppNavigation(navController: NavHostController,modifier:Modifier = Modifier){
    NavHost(
        navController=navController,
        startDestination = "leagues",
        modifier =modifier
    ){
        composable("leagues") { LeaguesScreen(navController) }
        composable("league_details/{leagueCode}") { backStackEntry ->
            val leagueCode = backStackEntry.arguments?.getString("leagueCode")?.toString()
            leagueCode?.let { LeaguesDetailsScreen(it) }
        }
    }


}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdvancedAndroidCourseTheme {
        Greeting("Android")
    }
}