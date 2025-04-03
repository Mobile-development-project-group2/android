package com.example.advancedandroidcourse.navigation.appbars
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.navigation.NavController


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
                    painter = painterResource(id = androidx.biometric.R.drawable.abc_vector_test),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
    )
}
