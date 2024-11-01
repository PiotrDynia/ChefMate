package com.example.chefmate.featureSearch.domain.usecase

import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.domain.util.methodResult.Result
import com.example.chefmate.core.domain.util.methodResult.DataError
import com.example.chefmate.core.domain.util.methodResult.Error
import com.example.chefmate.featureSearch.domain.repository.SearchRepository
import com.example.chefmate.featureSearch.presentation.SearchState
import retrofit2.HttpException
import java.io.IOException

class SearchRecipes(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(state: SearchState) : Result<GetRecipeResult, Error> {
        return try {
            val recipes = repository.getRecipes(state)
            Result.Success(recipes)
        } catch (e: HttpException) {
            val error = when (e.code()) {
                408 -> DataError.Network.REQUEST_TIMEOUT
                429 -> DataError.Network.TOO_MANY_REQUESTS
                413 -> DataError.Network.PAYLOAD_TOO_LARGE
                in 500..599 -> DataError.Network.SERVER_ERROR
                else -> DataError.Network.UNKNOWN
            }
            Result.Error(error)
        } catch (e: IOException) {
            Result.Error(DataError.Network.NO_INTERNET)
        }
    }
}