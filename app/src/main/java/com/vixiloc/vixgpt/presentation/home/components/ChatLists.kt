package com.vixiloc.vixgpt.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChatLists() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Question Is basically used to ask the question to each other.")
        Text(text = "This is the answer of the question, this answer is very long and it will be wrapped to the next line.")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChatListsPreview() {
    ChatLists()
}