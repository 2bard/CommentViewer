package com.twobard.techtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.ui.detail.DetailScreen
import com.twobard.techtest.ui.list.CommentListViewModel
import com.twobard.techtest.ui.list.ListScreen
import com.twobard.techtest.ui.theme.TechTestTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue


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

        //List screen
        composable("home") { backStackEntry ->
            val viewModel = hiltViewModel<CommentListViewModel>()
            val comments by viewModel.comments.collectAsState(initial = listOf())
            val onClickItem: ((Comment) -> Unit) = { navController.navigate("detail") }
            val onClickRefresh : (() -> Unit) = { viewModel.loadComments() }
            ListScreen(comments, onClickItem) {
                navController.navigate("detail")
            }
        }

        //Detail screen
        composable("detail") { backStackEntry ->
            DetailScreen() {
                navController.popBackStack()
            }
        }

    }
}
