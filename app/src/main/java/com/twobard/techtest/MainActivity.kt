package com.twobard.techtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.twobard.techtest.ui.detail.DetailScreenState
import com.twobard.techtest.ui.detail.ListScreenState
import com.twobard.techtest.ui.nav.Routes
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
            DetailScreenState(navController)
        }
    }
}


