package com.vixiloc.vixgpt.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.model.Settings
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: Chats)

    @Query("SELECT * from chats ORDER BY id ASC")
    fun getAllChats(): Flow<List<Chats>?>

    @Query("DELETE from chats")
    fun deleteAllChats()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSettings(data: Settings)

    @Query("SELECT * from settings WHERE id = 1 limit 1")
    fun getAllSettings(): Flow<Settings?>
}