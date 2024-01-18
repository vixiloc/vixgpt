package com.vixiloc.vixgpt.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.vixiloc.vixgpt.domain.model.Chats

@Composable
fun ChatLists(chats: Chats) {
    ListItem(
        headlineContent = {
            Text(
                text = chats.question,
                style = MaterialTheme.typography.headlineMedium,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
        },
        supportingContent = {
            Text(
                text = chats.answer,
                style = MaterialTheme.typography.bodySmall
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
            headlineColor = MaterialTheme.colorScheme.onSecondaryContainer,
            supportingColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    )
}

@Preview()
@Composable
fun ChatListsPreview() {
//    ChatLists()
}