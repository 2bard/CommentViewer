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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.twobard.techtest.ui.detail.DetailViewModel


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

//I've made this so we don't have to keep typing the routes as strings everywhere
enum class Routes(val route: String) {
     HOME("home"),
     DETAIL("detail")
}

@Composable
fun TechTestAppComposable() {
    val navController = rememberNavController()
    val startRoute = Routes.HOME.route

    //Using the Android navigation library, used Voyager in the past but wanted to try this
    NavHost(navController, startDestination = startRoute) {

        //List screen
        composable(Routes.HOME.route) { backStackEntry ->
            ListScreenState(navController)
        }

        //Detail screen
        composable(
            route = Routes.DETAIL.route + "/{commentId}", //Not sure if an enum class for args is overkill
            arguments = listOf(
            navArgument("commentId") { type = NavType.IntType }
        )) { backStackEntry ->
            val viewModel = hiltViewModel<DetailViewModel>()
            val comment by viewModel.comment.collectAsState()
            DetailScreen(comment) {
                navController.popBackStack()
            }
        }

    }
}

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun ListScreenState(navController: androidx.navigation.NavHostController) {

    //Trying to set up all the state handling here, so ListScreen is as simple as possible
    val context = LocalContext.current
    val viewModel = hiltViewModel<CommentListViewModel>()

    val snackbarHostState = remember { SnackbarHostState() }

    //click listeners
    val onClickItem: ((Comment) -> Unit) = { navController.navigate(Routes.DETAIL.route + "/" + it.id) }
    val onClickRefresh : (() -> Unit) = { viewModel.loadComments() }

    //StateFlow for list state
    val comments by viewModel.comments.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    //SharedFlow for errors
    val errors by viewModel.errors.collectAsState(null)

    //Never been a fan of this pattern but not sure of a better approach!
    LaunchedEffect(errors) {
        errors?.let {
            snackbarHostState.showSnackbar(context.getString(it.messageRes))
        }
    }

    ListScreen(comments, isLoading, snackbarHostState, onClickItem, onClickRefresh)
}
