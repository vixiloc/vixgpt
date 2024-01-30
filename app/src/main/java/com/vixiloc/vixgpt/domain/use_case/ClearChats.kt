package com.vixiloc.vixgpt.domain.use_case

import com.vixiloc.vixgpt.domain.repository.ChatsRepository

class ClearChats(private val repository: ChatsRepository) {

    suspend operator fun invoke() {
        repository.deleteAllChats()
    }
}