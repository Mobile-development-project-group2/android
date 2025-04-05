package com.example.advancedandroidcourse.navigation.appbars
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack


@Composable
fun AppTopBar(navController: NavController, title: String) {
    androidx.compose.material.TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleMedium, color = Color.White)
        },
        navigationIcon = {
            IconButton(onClick = {

                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
    )
}
