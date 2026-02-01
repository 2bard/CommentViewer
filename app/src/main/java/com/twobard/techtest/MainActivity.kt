package com.twobard.techtest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource


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
            ListScreenState(navController)
        }

        //Detail screen
//        composable("detail") { backStackEntry ->
//            DetailScreen() {
//                navController.popBackStack()
//            }
//        }

    }
}

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun ListScreenState(navController: androidx.navigation.NavHostController) {
    val context = LocalContext.current

    val viewModel = hiltViewModel<CommentListViewModel>()
    val snackbarHostState = remember { SnackbarHostState() }
    val comments by viewModel.comments.collectAsState(initial = listOf())
    val onClickItem: ((Comment) -> Unit) = { navController.navigate("detail") }
    val onClickRefresh : (() -> Unit) = { viewModel.loadComments() }
    val errors by viewModel.errors.collectAsState(null)

    //Never been a fan of this pattern
    LaunchedEffect(errors) {
        errors?.let {
            snackbarHostState.showSnackbar(context.getString(it.messageRes))
        }
    }

    ListScreen(comments, snackbarHostState, onClickItem, onClickRefresh)
}
