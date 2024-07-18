package com.example.practicesoft.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicesoft.Constants
import com.example.practicesoft.api.CountryService
import com.example.practicesoft.api.MuseumRepository
import com.example.practicesoft.api.NetworkResponse
import com.example.practicesoft.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: MuseumRepository
) : ViewModel() {
    val cityList = MutableLiveData<List<Country>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getCountries() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.getCities()) {
                is NetworkResponse.Success -> {
                    response.data?.countries?.let {
                        cityList.value = it
                        isLoading.value = false
                    }
                }
                is NetworkResponse.Error -> {
                    isLoading.value = false
                }
            }
        }
    }

}