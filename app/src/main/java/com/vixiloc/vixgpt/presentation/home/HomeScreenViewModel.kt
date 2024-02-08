package com.vixiloc.vixgpt.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.use_case.ClearChats
import com.vixiloc.vixgpt.domain.use_case.GetAllChats
import com.vixiloc.vixgpt.domain.use_case.GetAllSettings
import com.vixiloc.vixgpt.domain.use_case.GetChatById
import com.vixiloc.vixgpt.domain.use_case.InsertChat
import com.vixiloc.vixgpt.domain.use_case.SendChat
import com.vixiloc.vixgpt.domain.use_case.ValidateQuestion
import com.vixiloc.vixgpt.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getSettings: GetAllSettings,
    private val getChats: GetAllChats,
    private val sendChat: SendChat,
    private val clearChats: ClearChats,
    private val insertChat: InsertChat,
    private val validateQuestion: ValidateQuestion,
    private val getChatById: GetChatById,
) : ViewModel() {
    var state by mutableStateOf(HomeScreenState())

    fun setState(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.QuestionChanged -> {
                state = state.copy(question = event.question)
            }

            is HomeScreenEvent.Submit -> {
                submit()
            }

            is HomeScreenEvent.AnswerDismissed -> {
                state = state.copy(answer = "", question = "")
            }

            is HomeScreenEvent.ErrorAlertDismissed -> {
                state = state.copy(errorMessage = "")
            }

            is HomeScreenEvent.ClearAllChat -> {
                viewModelScope.launch {
                    clearChats()
                }
            }

            is HomeScreenEvent.ChatClicked -> {
                viewModelScope.launch {
                    getChat(event.id)
                }
            }
        }
    }

    private fun getChat(id: Int) {
        Log.d(TAG, "getChat: Called!")
        getChatById(id).onEach { chats ->
            state = state.copy(
                answer = chats?.answer ?: "",
                question = chats?.question ?: ""
            )
        }.launchIn(viewModelScope)
    }

    private fun submit() {
        state = state.copy(isLoading = true)
        val validate = validateQuestion(question = state.question)
        if (validate is Resource.Success) {
            if (state.settings?.model?.isNotEmpty() == true && state.settings?.apiKey?.isNotEmpty() == true) {
                viewModelScope.launch {
                    val chats = Chats(
                        question = state.question,
                        answer = state.answer,
                        model = state.settings?.model ?: "",
                    )
                    sendChat(
                        chats = chats,
                        token = state.settings?.apiKey ?: ""
                    ).onEach { resource ->
                        state = when (resource) {
                            is Resource.Success -> {
                                saveChat(answer = resource.data?.answer ?: "")
                                state.copy(
                                    answer = resource.data?.answer ?: "",
                                )
                            }

                            is Resource.Error -> {
                                state.copy(
                                    isLoading = false,
                                    errorMessage = resource.message ?: "",
                                    question = ""
                                )
                            }

                            is Resource.Loading -> {
                                state.copy(isLoading = true)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            } else {
                state = state.copy(errorMessage = "Please enter a valid API Key and Model")
            }
        } else {
            state = state.copy(
                isLoading = false,
                errorMessage = validate.message ?: ""
            )
        }
    }

    private fun saveChat(answer: String) {
        val chats = Chats(
            question = state.question,
            answer = answer,
            model = state.settings?.model ?: "",
        )

        viewModelScope.launch {
            insertChat(chats)
        }
        state = state.copy(isLoading = false)
    }

    init {
        viewModelScope.launch {
            getSettings().onEach {
                state = state.copy(settings = it)
            }.launchIn(viewModelScope)
            getChats().onEach { chats ->
                state = state.copy(chats = chats ?: emptyList())
            }.launchIn(viewModelScope)
        }
    }

}