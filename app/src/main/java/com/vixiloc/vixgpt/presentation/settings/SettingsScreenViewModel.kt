package com.vixiloc.vixgpt.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixgpt.domain.model.Settings
import com.vixiloc.vixgpt.domain.use_case.GetAllSettings
import com.vixiloc.vixgpt.domain.use_case.UpdateSettings
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val updateSettings: UpdateSettings,
    getSettings: GetAllSettings
) : ViewModel() {
    var state by mutableStateOf(SettingsScreenState())

    fun setState(event: SettingsScreenEvent) {
        when (event) {
            is SettingsScreenEvent.EnteredApiKey -> {
                state = state.copy(apiKey = event.apiKey)
            }

            is SettingsScreenEvent.EnteredAiModel -> {
                state = state.copy(aiModel = event.aiModel)
            }

            is SettingsScreenEvent.OpenSettingsSheet -> {
                state =
                    state.copy(openSettingsSheet = true, currentEditedSetting = event.editedSetting)
            }

            is SettingsScreenEvent.CloseSettingsSheet -> {
                state = state.copy(openSettingsSheet = false, currentEditedSetting = 0)
            }

            is SettingsScreenEvent.SubmitSettings -> {
                viewModelScope.launch {
                    val data = Settings(apiKey = state.apiKey, model = state.aiModel, id = 1)
                    updateSettings(data = data)
                    state = state.copy(openSettingsSheet = false)
                }
            }
        }
    }

    init {
        getSettings().onEach { settings ->
            state = settings?.let {
                state.copy(apiKey = it.apiKey ?: "", aiModel = it.model ?: "")
            } ?: state.copy(apiKey = "", aiModel = "")
        }.launchIn(viewModelScope)
    }
}