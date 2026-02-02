package com.twobard.techtest.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.twobard.techtest.R
import com.twobard.techtest.domain.repository.Comment
import com.twobard.techtest.ui.components.NavBarText
import com.twobard.techtest.ui.theme.ThemePadding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    comment: Comment?,
    onClickBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { NavBarText(stringResource(R.string.comment_detail)) },
                navigationIcon = {
                    IconButton(
                        onClick = { onClickBack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

            Box(modifier = Modifier.padding(ThemePadding.screenPadding())) {
                comment?.let {
                    DetailPanel(it)
                }
            }

        }
    }
}

@Composable
@Preview
fun DetailPanel(@PreviewParameter(CommentPreviewProvider::class) comment: Comment){

    Card {
        Column(modifier = Modifier.padding(ThemePadding.boxPadding())) {
            Column(modifier = Modifier
                .padding(ThemePadding.boxPadding())
                .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(ThemePadding.listPadding())) {
                Text(text = comment.name, style = MaterialTheme.typography.titleMedium)
                Text(text = comment.body,  style = MaterialTheme.typography.bodySmall)
            }

            CommentPropertyDivider()
            Column {
                PropertyRow(stringResource(R.string.id), comment.id.toString())
                CommentPropertyDivider()
                PropertyRow(stringResource(R.string.email), comment.email)
            }
        }

    }
}

@Composable
fun PropertyRow(key: String, value: String){

    Row(modifier = Modifier.padding(ThemePadding.boxPadding()), verticalAlignment = Alignment.CenterVertically){
        Text(modifier = Modifier.wrapContentSize(), text = key, style = MaterialTheme.typography.titleMedium)
        Text(modifier = Modifier.weight(1f), maxLines = 1, text = value, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.End, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
fun CommentPropertyDivider(){
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = ThemePadding.dividerThickness(),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}