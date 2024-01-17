package com.vixiloc.vixgpt.presentation.home

import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.model.Settings

data class HomeScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val answer: String = "",
    val question: String = "",
    val settings: Settings? = null,
    val chats: List<Chats> = emptyList()
)