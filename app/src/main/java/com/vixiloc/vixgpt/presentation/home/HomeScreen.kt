package com.vixiloc.vixgpt.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vixiloc.vixgpt.R
import com.vixiloc.vixgpt.presentation.home.components.BottomSheetAnswer
import com.vixiloc.vixgpt.presentation.home.components.ChatLists
import com.vixiloc.vixgpt.presentation.lottie.LottieAnimation
import com.vixiloc.vixgpt.presentation.navigations.MainRoute
import com.vixiloc.vixgpt.presentation.ui.theme.VixGPTTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
    navhostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.state
    val focusManager = LocalFocusManager.current

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            scope.launch {
                navhostController.navigate(MainRoute.Settings.name)
            }
        }) {
            Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.tertiary
                        )
                    )
                )
                .padding(paddingValues)
        ) {
            TextField(
                value = state.question,
                onValueChange = { viewModel.setState(HomeScreenEvent.QuestionChanged(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = { Text(text = "Ask your question") },
                leadingIcon = { Text(text = "❓") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Send,
                ),
                keyboardActions = KeyboardActions(onSend = {
                    focusManager.clearFocus()
                    viewModel.setState(HomeScreenEvent.Submit)
                })
            )

            if (state.chats.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    LottieAnimation(
                        modifier = Modifier.width(200.dp),
                        resource = R.raw.ask_me_1704295393783
                    )
                }
            }

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    LottieAnimation(
                        modifier = Modifier.width(150.dp),
                        resource = R.raw.writing_animation,
                    )
                }
            }

            if (state.answer.isNotBlank()) {
                BottomSheetAnswer(answer = state.answer, onDismiss = {
                    viewModel.setState(HomeScreenEvent.AnswerDismissed)
                })
            }
            if (state.errorMessage.isNotBlank()) {
                AlertDialog(
                    onDismissRequest = { viewModel.setState(HomeScreenEvent.ErrorAlertDismissed) },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.setState(HomeScreenEvent.ErrorAlertDismissed) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "OK",
                            )
                        }
                    },
                    title = {
                        Text(
                            text = "Error",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    text = {
                        Text(
                            text = state.errorMessage,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    })
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(state.chats) { chat ->
                    ChatLists(chats = chat)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    VixGPTTheme {
        Surface {
//            HomeScreen(navhostController = navhostController)
        }
    }
}