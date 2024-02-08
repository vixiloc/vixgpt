package com.vixiloc.vixgpt.presentation.home

sealed class HomeScreenEvent {
    data class QuestionChanged(val question: String) : HomeScreenEvent()
    data class ChatClicked(val id: Int) : HomeScreenEvent()
    data object Submit : HomeScreenEvent()
    data object AnswerDismissed : HomeScreenEvent()
    data object ErrorAlertDismissed : HomeScreenEvent()
    data object ClearAllChat : HomeScreenEvent()
}