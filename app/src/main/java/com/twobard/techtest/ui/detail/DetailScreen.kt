package com.twobard.techtest.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.twobard.techtest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onClickBack : () -> Unit = {}
) {

    Scaffold(
        topBar =  {
            TopAppBar(
                title = { Text(stringResource(R.string.comment_detail)) },
                navigationIcon = {
                    Text(
                        text = stringResource(R.string.back),
                        modifier = Modifier
                            .clickable { onClickBack() }
                    )
                }
            )
        }
    ) {
            paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Box(modifier = Modifier.clickable{

            }) {
                Text("test123")
            }
        }
    }

}