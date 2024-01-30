package com.vixiloc.vixgpt.data.repository

import com.vixiloc.vixgpt.data.source.local.room.ChatsDao
import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.repository.ChatsRepository
import kotlinx.coroutines.flow.Flow

class ChatsRepositoryImpl(private val dao: ChatsDao) : ChatsRepository {
    override suspend fun insert(data: Chats) {
        dao.insert(data)
    }

    override fun getAllChats(): Flow<List<Chats>?> {
        return dao.getAllChats()
    }

    override suspend fun deleteAllChats() {
        dao.deleteAllChats()
    }
}