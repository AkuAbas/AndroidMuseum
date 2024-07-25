package com.example.practicesoft.api

import com.example.practicesoft.Constants.API_KEY
import com.example.practicesoft.local.Player
import com.example.practicesoft.local.PlayerDAO
import com.example.practicesoft.model.Village
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MuseumRepository @Inject constructor(
    private val service: CountryService,
    private val playerDAO: PlayerDAO
) {

    suspend fun getCities() = safeApiRequest {
        service.getAllCities()
    }

    suspend fun getVillages(city: String) = safeApiRequest {
        service.getAllVillages(city = city)
    }

    suspend fun getMuseums(city: String, village: String) = safeApiRequest {
        service.getAllMuseums(city, village)
    }

    fun addPlayer(player: Player) = playerDAO.insertPlayer(player)
    suspend fun getAllPlayers() = playerDAO.getPlayer()


    private suspend fun <T> safeApiRequest(apicall: suspend () -> Response<T>): Flow<NetworkResponse<T & Any>> =
        flow {
            emit(NetworkResponse.Loading())
            try {
                val response = apicall.invoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(NetworkResponse.Success(it))
                    } ?: emit(NetworkResponse.Error("Empty"))
                } else {
                    emit(NetworkResponse.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)


}