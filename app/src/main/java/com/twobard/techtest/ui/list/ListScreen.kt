package com.twobard.techtest.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.twobard.techtest.R
import com.twobard.techtest.domain.repository.Comment
import kotlin.collections.listOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: CommentListViewModel = viewModel(),
    onClickItem: () -> Unit = {}
) {

    val items by viewModel.comments.collectAsState(initial = emptyList())

    Scaffold(
        topBar =  {
            TopAppBar(
                title = { Text(stringResource(R.string.comment_list)) }
            )
        }
    ) {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Box(modifier = Modifier.clickable{
                onClickItem()
            }) {
                Text("test123")
            }
        }
    }

}

class CommentPreviewProvider : PreviewParameterProvider<List<Comment>> {
    override val values = sequenceOf(
        listOf(
            Comment(postId = 1, id = 1, name = "C Comment", email = "test@test.com", body = "Hello 1"),
            Comment(postId = 2, id = 2, name = "A Comment", email = "test2@test.com", body = "Hello 2"),
            Comment(postId = 3, id = 3, name = "B Comment", email = "test3@test.com", body = "Hello 3")
        )
    )
}

@Preview
@Composable
fun ItemList(
    @PreviewParameter(CommentPreviewProvider::class) values: List<Comment>
) {
    LazyColumn() {
        items(values.size) {
            Text(
                text = values[it].name
            )
        }
    }
}