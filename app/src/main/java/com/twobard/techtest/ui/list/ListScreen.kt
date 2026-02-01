package com.twobard.techtest.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListScreen(
    viewModel: CommentListViewModel = viewModel()
) {

    Box {
        Text("test123")
    }

}