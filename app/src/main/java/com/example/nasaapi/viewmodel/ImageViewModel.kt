package com.example.nasaapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapi.model.DAY.Day
import com.example.nasaapi.network.ApiClient
import com.example.nasaapi.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {

    private val retrofit: ApiService = ApiClient.getApiClient().create(ApiService::class.java)
    private val imageList: MutableLiveData<Day> = MutableLiveData()
    fun getCollection() = imageList as LiveData<Day>
    fun getData(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val image = retrofit.getImageOfTheDay(
                api = "NNlP51YLYpxPjy6tswI6Pw4SIhYsuheGdHCvz2pI",
                data = q
            )
            if (image.isSuccessful) {
                imageList.postValue(image.body())
            }
        }
    }
}