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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VillageViewModel @Inject constructor(
    private val repository: MuseumRepository
) : ViewModel() {

    val uiState = MutableLiveData<VillageUiState>()

    /* response.data?.villages?.let {
         villageList.value = it
         isLoading.value = false
     }*/


    fun getVillages(city: String) {
        viewModelScope.launch {
            repository.getVillages(city).collectLatest {
                when (it) {
                    is NetworkResponse.Success -> {
                        it.data?.villages?.let {
                            uiState.value = VillageUiState.VillageList(it)
                        }
                    }

                    is NetworkResponse.Error -> {
                        uiState.value = VillageUiState.Error(it.message.toString())
                    }

                    is NetworkResponse.Loading -> {
                        uiState.value = VillageUiState.Loading
                    }
                }
            }
        }
    }

    sealed class VillageUiState {
        data class VillageList(val villages: List<Village>) : VillageUiState()
        object Loading : VillageUiState()
        data class Error(val message: String = "") : VillageUiState()
    }
}