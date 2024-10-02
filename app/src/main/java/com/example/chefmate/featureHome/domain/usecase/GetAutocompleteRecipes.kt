package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResult
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.DataError
import retrofit2.HttpException
import java.io.IOException

class GetAutocompleteRecipes(
    private val apiService: APIService
) {

    suspend operator fun invoke(input: String) : Result<GetRecipesAutocompleteResult, DataError.Network> {
        return try {
            val recipes = apiService.getAutocompleteRecipes(input)
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