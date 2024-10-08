package com.example.chefmate.featureSearch.domain.usecase

import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.DataError
import com.example.chefmate.core.domain.util.error.Error
import com.example.chefmate.core.domain.util.error.ValidationError
import com.example.chefmate.featureSearch.domain.repository.SearchRepository
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection
import retrofit2.HttpException
import java.io.IOException

class SearchRecipes(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(filterSelection: SearchFilterSelection) : Result<GetRecipeResult, Error> {
        if (filterSelection.query.isBlank()) {
            return Result.Error(ValidationError.EMPTY)
        }
        if (filterSelection.query.length < 3) {
            return Result.Error(ValidationError.TOO_SHORT)
        }
        return try {
            val recipes = repository.getRecipes(filterSelection)
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