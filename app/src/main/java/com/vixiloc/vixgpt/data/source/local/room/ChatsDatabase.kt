package com.vixiloc.vixgpt.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vixiloc.vixgpt.domain.model.Chats
import com.vixiloc.vixgpt.domain.model.Settings

@Database(entities = [Chats::class, Settings::class], version = 1, exportSchema = false)
abstract class ChatsDatabase : RoomDatabase() {
    abstract fun chatsDao(): ChatsDao

    companion object {
        @Volatile
        private var INSTANCE: ChatsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ChatsDatabase {
            if (INSTANCE == null) {
                synchronized(ChatsDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ChatsDatabase::class.java, "chats_db"
                    )
                        .build()
                }
            }
            return INSTANCE as ChatsDatabase
        }
    }
}