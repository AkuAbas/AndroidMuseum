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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: MuseumRepository
) : ViewModel() {

    val uiState = MutableLiveData<CityUiState>()


    fun getCountries() {
        viewModelScope.launch {
            repository.getCities().collectLatest {
                when (it) {
                    is NetworkResponse.Loading -> {
                        uiState.value = CityUiState.Loading
                    }

                    is NetworkResponse.Success -> {
                        it.data?.countries?.let {
                            uiState.value = CityUiState.CityList(it)
                        }
                    }

                    is NetworkResponse.Error -> {
                        it.message?.let {
                            uiState.value = CityUiState.Error(it)
                        }
                    }
                }
            }
        }
    }

    sealed class CityUiState {
        data class CityList(val cities: List<Country>) : CityUiState()
        object Loading : CityUiState()
        data class Error(val message: String = "") : CityUiState()
    }
}