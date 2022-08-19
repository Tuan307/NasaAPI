package com.example.nasaapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapi.db.HistoryRepo
import com.example.nasaapi.model.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: HistoryRepo) : ViewModel() {

    val listOfHistory: MutableLiveData<List<SearchItem>> = MutableLiveData()
    fun getHistory() = listOfHistory as LiveData<List<SearchItem>>

    fun getAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getAllHistory()
            listOfHistory.postValue(list)
        }
    }

    fun deleteHistory(history: SearchItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHistory(history)
        }
    }

    fun addHistory(history: SearchItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHistory(history)
        }
    }
}