package com.example.kaamelottcitations.ui

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun Toolbar(title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        }
    )
}