package com.twobard.techtest.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun DetailScreenState(navController: NavController){
    val viewModel = hiltViewModel<DetailViewModel>()
    val comment by viewModel.comment.collectAsState()
    DetailScreen(comment) {
        navController.popBackStack()
    }
}