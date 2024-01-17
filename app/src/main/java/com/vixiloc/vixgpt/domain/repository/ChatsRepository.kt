package com.vixiloc.vixgpt.domain.repository

import com.vixiloc.vixgpt.domain.model.Chats
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {
    suspend fun insert(data: Chats)
    fun getAllChats(): Flow<List<Chats>?>
    fun deleteAllChats()
}