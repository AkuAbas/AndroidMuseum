package com.example.practicesoft.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicesoft.Constants.API_KEY
import com.example.practicesoft.api.CountryService
import com.example.practicesoft.api.MuseumRepository
import com.example.practicesoft.api.NetworkResponse
import com.example.practicesoft.model.Museum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MuseumViewModel @Inject constructor(
    private val repository: MuseumRepository
) : ViewModel() {
    val museumList = MutableLiveData<List<Museum>>()
    val isLoading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()

    fun getMuseums(city: String, village: String) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.getMuseums(city, village)) {
                is NetworkResponse.Success -> {
                    response.data?.museums?.let {
                        if (it.isNotEmpty()) {
                            museumList.value = it
                            isLoading.value = false
                        } else {
                            isEmpty.value = true
                            isLoading.value = false
                        }
                    }
                }

                is NetworkResponse.Error -> {
                    isLoading.value = false
                }
            }
        }
    }

}