package com.twobard.techtest.ui.list

import ListItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.stylusHoverIcon
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.twobard.techtest.R
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.ui.components.EmptyListState
import com.twobard.techtest.ui.components.NavBarText
import com.twobard.techtest.ui.theme.ThemePadding


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ListScreen(
    @PreviewParameter(CommentListPreviewProvider::class) items: List<Comment>,
    onClickItem: (Comment) -> Unit = {},
    onClickReload: () -> Unit = {}
) {

    Scaffold(
        topBar =  {
            TopAppBar(
                title = {
                    NavBarText(stringResource(R.string.comment_list))
                }
            )
        }
    ) {
        paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            ItemList(
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
    onClickItem: (Comment) -> Unit = {},
    onClickReload: () -> Unit = {}
) {
    Box(modifier = Modifier.padding(ThemePadding().screenPadding())){
        if(values.isEmpty()){
            EmptyListState(onClickReload)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(ThemePadding().listPadding())) {
                items(values.size) {
                    ListItem(comment = values[it], onClickItem = onClickItem)
                }
            }
        }
    }
}





