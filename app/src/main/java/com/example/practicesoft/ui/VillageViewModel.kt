package com.example.practicesoft.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicesoft.Constants.API_KEY
import com.example.practicesoft.api.CountryService
import com.example.practicesoft.api.MuseumRepository
import com.example.practicesoft.api.NetworkResponse
import com.example.practicesoft.model.Village
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VillageViewModel @Inject constructor(
    private val repository: MuseumRepository
) : ViewModel() {
    val villageList = MutableLiveData<List<Village>>()
    val isLoading = MutableLiveData<Boolean>()


    fun getVillages(city: String) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = repository.getVillages(city)) {
                is NetworkResponse.Success -> {
                    response.data?.villages?.let {
                        villageList.value = it
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