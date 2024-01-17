package com.vixiloc.vixgpt.domain.use_case

import android.util.Log
import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.repository.ChatsRepository
import kotlinx.coroutines.flow.Flow

class GetAllChats(private val repository: ChatsRepository) {
    operator fun invoke(): Flow<List<Chats>?> {
        return repository.getAllChats()
    }
}