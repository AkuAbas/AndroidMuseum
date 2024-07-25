package com.example.practicesoft.local

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicesoft.api.MuseumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val repository: MuseumRepository) : ViewModel() {

    val playerList = MutableLiveData<List<Player>>()

    fun addPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayer(player)
            getAllPlayers()
        }
    }

    fun getAllPlayers() {
        viewModelScope.launch {
            val list = repository.getAllPlayers()
            playerList.value = list
        }
    }
}