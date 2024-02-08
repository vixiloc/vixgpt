package com.vixiloc.vixgpt.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vixiloc.vixgpt.presentation.settings.components.BottomSheetSettings
import com.vixiloc.vixgpt.presentation.ui.theme.VixGPTTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsScreenViewModel = koinViewModel(),
    navhostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.state
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Settings", style = MaterialTheme.typography.titleMedium) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        navhostController.navigateUp()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            })
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                item {
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.setState(SettingsScreenEvent.OpenSettingsSheet(1))
                            },
                        headlineContent = { Text(text = "API Key") },
                        supportingContent = { Text(text = "Set your OpenAI API Key before ask question") },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Outlined.ArrowRight,
                                contentDescription = null
                            )
                        }
                    )
                    Divider()
                }
                item {
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.setState(SettingsScreenEvent.OpenSettingsSheet(2))
                            },
                        headlineContent = { Text(text = "AI Model") },
                        supportingContent = { Text(text = "Set your OpenAI model name") },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Outlined.ArrowRight,
                                contentDescription = null
                            )
                        }
                    )
                    Divider()
                }
                item {
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // TODO: Open Screen
                            },
                        headlineContent = { Text(text = "Open source software attribution") },
                        supportingContent = { Text(text = "open source software attribution") },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Outlined.ArrowRight,
                                contentDescription = null
                            )
                        }
                    )
                    Divider()
                }
            }
            if (state.openSettingsSheet) {
                BottomSheetSettings(onDismiss = { viewModel.setState(SettingsScreenEvent.CloseSettingsSheet) }) {
                    OutlinedTextField(
                        value = when (state.currentEditedSetting) {
                            1 -> state.apiKey
                            2 -> state.aiModel
                            else -> ""
                        },
                        onValueChange = {
                            when (state.currentEditedSetting) {
                                1 -> viewModel.setState(SettingsScreenEvent.EnteredApiKey(it))
                                2 -> viewModel.setState(SettingsScreenEvent.EnteredAiModel(it))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 16.dp),
                        visualTransformation = if (state.currentEditedSetting == 1) {
                            // Hide API Key
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp),
                        onClick = { viewModel.setState(SettingsScreenEvent.SubmitSettings) }) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    VixGPTTheme {
        Surface {
//            SettingsScreen(navhostController = navhostController)
        }
    }
}