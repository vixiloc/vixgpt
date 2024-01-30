package com.vixiloc.vixgpt.di

import com.vixiloc.vixgpt.presentation.home.HomeScreenViewModel
import com.vixiloc.vixgpt.presentation.settings.SettingsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeScreenViewModel(
            getSettings = get(),
            getChats = get(),
            sendChat = get(),
            validateQuestion = get(),
            insertChat = get(),
            clearChats = get(),
        )
    }
    viewModel {
        SettingsScreenViewModel(
            updateSettings = get(),
            getSettings = get(),
        )
    }
}