package com.example.practicesoft.model


import com.google.gson.annotations.SerializedName

data class Museum(
    @SerializedName("address")
    val address: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("details")
    val details: String?,
    @SerializedName("district")
    val district: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("workingTime")
    val workingTime: String?
)