package com.twobard.techtest.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NavBarText(text: String){
    Text(text, style = MaterialTheme.typography.titleLarge)
}