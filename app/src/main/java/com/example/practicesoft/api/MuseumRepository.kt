package com.example.practicesoft.api

import com.example.practicesoft.Constants.API_KEY
import com.example.practicesoft.model.Village
import retrofit2.Response
import javax.inject.Inject

class MuseumRepository @Inject constructor(private val service: CountryService) {

    suspend fun getCities() = safeApiRequest {
        service.getAllCities()
    }

    suspend fun getVillages(city: String) = safeApiRequest {
        service.getAllVillages(city = city)
    }

    suspend fun getMuseums(city: String, village: String) = safeApiRequest {
        service.getAllMuseums(city, village)
    }


    private suspend fun <T> safeApiRequest(apicall: suspend () -> Response<T>): NetworkResponse<T> {
        try {
            val response = apicall.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    return NetworkResponse.Success(it)
                } ?: return NetworkResponse.Error("Empty")
            } else {
                return NetworkResponse.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            return NetworkResponse.Error(e.localizedMessage.toString())
        }
    }
}