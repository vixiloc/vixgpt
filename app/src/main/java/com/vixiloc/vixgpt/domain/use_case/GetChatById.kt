package com.vixiloc.vixgpt.domain.use_case

import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.repository.ChatsRepository
import kotlinx.coroutines.flow.Flow

class GetChatById(private val repository: ChatsRepository) {

    operator fun invoke(id: Int): Flow<Chats?> {
        return repository.getChatById(id)
    }
}