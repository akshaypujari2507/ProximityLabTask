package com.proximitylabtask.data.remote.models

data class AirQuality(
    val city: String?,
    val aqi: Float = 0f,
    val updatedTime: Long = System.currentTimeMillis()
)