package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.domain.util.Result
import com.example.chefmate.core.domain.util.error.DataError
import com.example.chefmate.featureHome.domain.repository.HomeRepository
import retrofit2.HttpException
import java.io.IOException

class FetchRandomRecipes(
    private val homeRepository: HomeRepository
) {

    suspend operator fun invoke() : Result<GetRandomRecipeResult, DataError.Network> {
        return try {
            val recipes = homeRepository.getRandomRecipes()
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