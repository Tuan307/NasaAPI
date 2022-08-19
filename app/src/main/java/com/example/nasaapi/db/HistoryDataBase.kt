package com.example.nasaapi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nasaapi.model.SearchItem

@Database(entities = [SearchItem::class], version = 1)
abstract class HistoryDataBase : RoomDatabase() {
    companion object {
        private var instance: HistoryDataBase? = null

        @Synchronized
        fun getInstance(context: Context): HistoryDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDataBase::class.java,
                    "history"
                ).build()
            }
            return instance as HistoryDataBase
        }
    }

    abstract fun historyDAO(): DatabaseService
}