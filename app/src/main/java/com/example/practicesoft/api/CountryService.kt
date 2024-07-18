package com.example.practicesoft.api

import com.example.practicesoft.Constants.API_KEY
import com.example.practicesoft.model.Country
import com.example.practicesoft.model.CountryResponse
import com.example.practicesoft.model.MuseumResponse
import com.example.practicesoft.model.VillageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {

    @GET("museum/cities")
    suspend fun getAllCities(): Response<CountryResponse>

    @GET("museum/cities")
    suspend fun getAllVillages(
        @Query("city") city: String
    ): Response<VillageResponse>

    @GET("museum")
    suspend fun getAllMuseums(
        @Query("city") city: String,
        @Query("district") village: String
    ): Response<MuseumResponse>
}