package com.example.practicesoft.model


import com.google.gson.annotations.SerializedName

data class MuseumResponse(
    @SerializedName("creditUsed")
    val creditUsed: Int?,
    @SerializedName("data")
    val museums: List<Museum>?,
    @SerializedName("endpoint")
    val endpoint: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("messageTR")
    val messageTR: String?,
    @SerializedName("rowCount")
    val rowCount: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("systemTime")
    val systemTime: Int?
)