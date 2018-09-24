package com.toolslab.gooddog.base_network.model

import com.squareup.moshi.Json

data class AllBreeds(
        @Json(name = "status") val status: String,
        @Json(name = "message") val message: Map<String, List<Any>>
)
