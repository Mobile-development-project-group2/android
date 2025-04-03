package com.example.advancedandroidcourse.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.advancedandroidcourse.R
import com.example.advancedandroidcourse.utils.leagues

data class League(val id: Number,val name: String, val logoUrl: String,val code: String)

@Composable
fun LeaguesScreen(navController:NavController){



    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(leagues) { league ->
            LeagueRow(league,onClick = {
                Log.d("LeagueId", "League Id: ${league.code}")
                navController.navigate("league_details/${league.code}")
            })
        }
    }

}



@Composable
fun LeagueRow(league: League,onClick:()-> Unit) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick()},
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(league.logoUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "League Logo",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(64.dp)

        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = league.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}





