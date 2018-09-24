package com.toolslab.gooddog.base_repository

import com.toolslab.gooddog.base_repository.exception.NoConnectionException
import com.toolslab.gooddog.base_repository.exception.NotFoundException
import com.toolslab.gooddog.base_repository.exception.RepositoryException
import com.toolslab.gooddog.base_repository.exception.UnavailableException
import io.reactivex.Single
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_UNAVAILABLE
import javax.inject.Inject

internal class ErrorHandler @Inject constructor() {

    internal fun <T> handle(throwable: Throwable): Single<T> =
            when (throwable) {
                is IOException -> Single.error(NoConnectionException(throwable))
                is HttpException -> when (throwable.code()) {
                    HTTP_NOT_FOUND -> Single.error(NotFoundException(throwable))
                    HTTP_UNAVAILABLE -> Single.error(UnavailableException(throwable))
                    else -> Single.error(RepositoryException(throwable))
                }
                else -> Single.error(RepositoryException(throwable))
            }

}
