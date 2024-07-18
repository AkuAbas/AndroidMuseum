package com.example.practicesoft.model


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("cities")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)