package com.toolslab.gooddog.base_network.service

import com.toolslab.gooddog.base_network.ApiEndpoint.Endpoint.BREEDS
import com.toolslab.gooddog.base_network.ApiEndpoint.Endpoint.RANDOM_IMAGE_OF_BREED
import com.toolslab.gooddog.base_network.ApiEndpoint.Path.BREED
import com.toolslab.gooddog.base_network.model.AllBreeds
import com.toolslab.gooddog.base_network.model.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(BREEDS)
    fun listAllBreeds(
    ): Single<AllBreeds>

    @GET(RANDOM_IMAGE_OF_BREED)
    fun randomImageOfBreed(
            @Path(BREED) breed: String
    ): Single<ImageResponse>

}
