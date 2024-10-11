package com.example.chefmate.featureSearch.domain.usecase

import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.Error
import com.example.chefmate.core.domain.util.error.ValidationError

class SearchRecipes {

    operator fun invoke(query: String) : Result<Unit, Error> {
        if (query.isBlank()) {
            return Result.Error(ValidationError.EMPTY)
        }
        if (query.length < 3) {
            return Result.Error(ValidationError.TOO_SHORT)
        }
        return Result.Success(Unit)
//        return try {
//            val recipes = repository.getRecipes(filterSelection)
//            Result.Success(recipes)
//        } catch (e: HttpException) {
//            val error = when (e.code()) {
//                408 -> DataError.Network.REQUEST_TIMEOUT
//                429 -> DataError.Network.TOO_MANY_REQUESTS
//                413 -> DataError.Network.PAYLOAD_TOO_LARGE
//                in 500..599 -> DataError.Network.SERVER_ERROR
//                else -> DataError.Network.UNKNOWN
//            }
//            Result.Error(error)
//        } catch (e: IOException) {
//            Result.Error(DataError.Network.NO_INTERNET)
//        }
    }
}