package com.toolslab.gooddog.base_repository

import com.toolslab.gooddog.base_network.service.ApiService
import com.toolslab.gooddog.base_repository.model.DogImage
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class DogImageRepository @Inject constructor() {

    @Inject
    internal lateinit var coworkingMapApi: ApiService

    @Inject
    internal lateinit var errorHandler: ErrorHandler

    fun randomDogImage(): Single<DogImage> {
        lateinit var breed: String
        return coworkingMapApi.listAllBreeds()
                .flatMap {
                    val breeds = it.message.keys.toList()
                    breed = breeds.random()
                    coworkingMapApi.randomImageOfBreed(breed)
                }
                .onErrorResumeNext {
                    errorHandler.handle(it)
                }
                .map {
                    DogImage(breed, it.message)
                }
    }

    /**
     * Returns a random element from a list.
     */
    private fun <E> List<E>.random(): E = get(Random().nextInt(size))

}
