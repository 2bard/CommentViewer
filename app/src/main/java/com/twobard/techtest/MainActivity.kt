package com.twobard.techtest

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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.twobard.techtest.ui.detail.DetailScreen
import com.twobard.techtest.ui.list.CommentListViewModel
import com.twobard.techtest.ui.list.ListScreen
import com.twobard.techtest.ui.theme.TechTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechTestTheme {
                TechTestAppComposable()
            }
        }
    }
}

@Composable
fun TechTestAppComposable() {
    val navController = rememberNavController()
    val startRoute = "home"
    NavHost(navController, startDestination = startRoute) {
        composable("home") { backStackEntry ->
            val viewModel = hiltViewModel<CommentListViewModel>()
            ListScreen(viewModel) {
                navController.navigate("detail")
            }
        }

        composable("detail") { backStackEntry ->
            DetailScreen()
        }

    }
}
