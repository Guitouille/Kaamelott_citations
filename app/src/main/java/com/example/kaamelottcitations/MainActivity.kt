package com.example.kaamelottcitations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.kaamelottcitations.navigation.NavigationHost
import com.example.kaamelottcitations.ui.quotes.CharacterQuotesViewModel
import com.example.kaamelottcitations.ui.theme.KaamelottCitationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaamelottCitationsTheme {
                NavigationHost()
            }
        }
    }
}