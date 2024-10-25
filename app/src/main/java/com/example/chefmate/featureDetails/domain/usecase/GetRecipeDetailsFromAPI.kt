package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.core.data.api.dto.RecipeDetails
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.DataError
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository
import retrofit2.HttpException
import java.io.IOException

class GetRecipeDetailsFromAPI(
    private val repository: DetailsRepository
) {

    suspend operator fun invoke(id: Int) : Result<RecipeDetails, DataError.Network> {
        return try {
            val recipe = repository.getRecipeDetailsFromAPI(id)
            Result.Success(recipe)
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