package com.twobard.techtest.ui.list

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController

import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.ui.nav.Routes

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun ListScreenState(navController: NavHostController) {

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