package com.hsnozan.favoriterestaurants.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by ozan.al on 13.02.2022.
 */
abstract class UseCase<out Type : Any, in Params> {

    protected abstract suspend fun buildUseCase(params: Params): Type

    suspend fun run(params: Params): Either<Throwable, Type> = withContext(Dispatchers.IO) {
        try {
            Either.Right(buildUseCase(params))
        } catch (failure: Throwable) {
            Either.Left(failure)
        }
    }
}