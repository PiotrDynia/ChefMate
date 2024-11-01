package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem
import com.example.chefmate.core.domain.util.methodResult.Result
import com.example.chefmate.core.domain.util.methodResult.DataError
import com.example.chefmate.featureHome.domain.repository.HomeRepository
import retrofit2.HttpException
import java.io.IOException

class GetAutocompleteRecipes(
    private val homeRepository: HomeRepository
) {

    suspend operator fun invoke(input: String) : Result<ArrayList<GetRecipesAutocompleteResultItem>, DataError.Network> {
        return if(input.length >= 3) {
            try {
                val recipes = homeRepository.getAutocompleteRecipes(input)
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
        } else {
          Result.Success(ArrayList())
        }
    }
}