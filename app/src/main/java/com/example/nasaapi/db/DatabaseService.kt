package com.example.nasaapi.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nasaapi.model.SearchItem

@Dao
interface DatabaseService {

    @Insert
    suspend fun addHistory(searchItem: SearchItem)

    @Query("SELECT * FROM history")
    suspend fun getAllHistory(): List<SearchItem>

    @Delete
    suspend fun deleteHistory(searchItem: SearchItem)
}