package com.toolslab.gooddog.base_network.model

import com.squareup.moshi.Json

data class ImageResponse(
        @Json(name = "status") val status: String,
        @Json(name = "message") val message: String
)
