package com.example.nasaapi.db

import com.example.nasaapi.model.SearchItem

class HistoryRepo(private val historyDao: DatabaseService) {

    suspend fun getAllHistory(): List<SearchItem> {
        return historyDao.getAllHistory()
    }

    suspend fun addHistory(searchItem: SearchItem) {
        historyDao.addHistory(searchItem)
    }

    suspend fun deleteHistory(searchItem: SearchItem) {
        historyDao.deleteHistory(searchItem)
    }
}