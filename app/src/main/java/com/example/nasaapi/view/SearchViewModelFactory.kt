package com.example.nasaapi.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaapi.db.HistoryRepo
import com.example.nasaapi.viewmodel.SearchViewModel

class SearchViewModelFactory(private val repository: HistoryRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}