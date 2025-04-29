package com.cvirn.moviesearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cvirn.moviesearch.navigation.AppNavigation
import com.cvirn.moviesearch.ui.theme.MovieSearchTheme
import com.cvirn.moviesearch.viewmodel.SharedViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieSearchTheme {
                val navController = rememberNavController()
                val sharedViewModel: SharedViewModel = koinViewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(navController, sharedViewModel)
                }
            }
        }
    }
}
