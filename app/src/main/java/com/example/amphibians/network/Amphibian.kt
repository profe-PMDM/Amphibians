package com.example.amphibians.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("description")
    val description: String,
    @SerialName("img_src")
    val imgSrc: String,
)
