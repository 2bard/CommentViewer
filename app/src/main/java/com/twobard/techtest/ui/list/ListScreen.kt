package com.twobard.techtest.ui.list

import CommentListPreviewProvider
import ListItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.twobard.techtest.R
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.ui.components.EmptyListState
import com.twobard.techtest.ui.components.LoadingState
import com.twobard.techtest.ui.components.NavBarText
import com.twobard.techtest.ui.theme.ThemePadding


@Preview
@Composable
fun ListScreenIsLoadingPreview() {
    ListScreen(
        items = emptyList(),
        isLoading = true,
        onClickReload = {},
        snackbarHostState = SnackbarHostState()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ListScreen(
    @PreviewParameter(CommentListPreviewProvider::class) items: List<Comment>,
    isLoading: Boolean = false,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    onClickItem: (Comment) -> Unit = {},
    onClickReload: () -> Unit = {}
) {

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar =  {
            TopAppBar(
                title = {
                    NavBarText(stringResource(R.string.comment_list))
                }
            )
        }
    ) {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize(), contentAlignment = Alignment.Center) {
            ItemList(
                isLoading = isLoading,
                values = items,
                onClickItem = onClickItem,
                onClickReload = onClickReload
            )
        }
    }

}

@Preview
@Composable
fun ItemList(
    @PreviewParameter(CommentListPreviewProvider::class) values: List<Comment>,
    isLoading: Boolean,
    onClickItem: (Comment) -> Unit = {},
    onClickReload: () -> Unit = {}
) {
    Box(modifier = Modifier.padding(ThemePadding.screenPadding()).fillMaxSize()){

        //Not sure what the best UX is here
        if(isLoading) {
            LoadingState()
        } else if(values.isEmpty()){
            EmptyListState(onClickReload)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(ThemePadding.listPadding())) {
                items(values.size) {
                    ListItem(comment = values[it], onClickItem = onClickItem)
                }
            }
        }
    }
}





