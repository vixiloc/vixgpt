package com.vixiloc.vixgpt.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vixiloc.vixgpt.domain.model.Chats

@Composable
fun ChatLists(chats: Chats, onClick: (Int) -> Unit) {
    ListItem(
        modifier = Modifier
            .padding(3.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable { onClick(chats.id ?: 0) },
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
                style = MaterialTheme.typography.bodySmall,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
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