package com.toolslab.gooddog.base_network

import com.toolslab.gooddog.base_network.ApiEndpoint.Path.BREED

internal object ApiEndpoint {

    internal object Path {
        internal const val BREED = "breed"
    }

    internal object Endpoint {
        internal const val BREEDS = "breeds/list/all"
        internal const val RANDOM_IMAGE_OF_BREED = "breed/{$BREED}/images/random"
    }

    internal const val API_BASE_URL = "https://dog.ceo/api/"

}
