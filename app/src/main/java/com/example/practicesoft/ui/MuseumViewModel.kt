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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MuseumViewModel @Inject constructor(
    private val repository: MuseumRepository
) : ViewModel() {

    val uiState = MutableLiveData<MuseumUiState>()

    fun getMuseums(city: String, village: String) {
        viewModelScope.launch {
            repository.getMuseums(city, village).collectLatest {
                when (it) {
                    is NetworkResponse.Loading -> uiState.value = MuseumUiState.Loading
                    is NetworkResponse.Success -> {
                        it.data?.museums?.let {
                            if (it.isNotEmpty()) {
                                uiState.value = MuseumUiState.MuseumList(it)
                            } else {
                                uiState.value = MuseumUiState.IsEmpty
                            }
                        }
                    }

                    is NetworkResponse.Error -> {
                        uiState.value = MuseumUiState.Error(it.message.toString())
                    }
                }
            }

        }
    }

    sealed class MuseumUiState {
        data class MuseumList(val museums: List<Museum>) : MuseumUiState()
        data class Error(val message: String = "") : MuseumUiState()
        object Loading : MuseumUiState()
        object IsEmpty : MuseumUiState()
    }


}