package com.example.chefmate.featureHome.data.repository

import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem
import com.example.chefmate.featureHome.domain.repository.HomeRepository
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class FakeHomeRepository : HomeRepository {

    var shouldReturnError = false
    var httpErrorCode: Int? = null
    var recipes: GetRecipeResult = GetRecipeResult(emptyList())
    var randomRecipes: GetRandomRecipeResult = GetRandomRecipeResult(emptyList())
    var autocompleteResults: ArrayList<GetRecipesAutocompleteResultItem> = ArrayList()

    override suspend fun getRecipes(
        cuisines: String,
        diets: String,
        intolerances: String,
        mealTypes: String
    ): GetRecipeResult {
        if (shouldReturnError) {
            if (httpErrorCode == 0) {
                throw IOException()
            }
            throw HttpException(
                Response.error<String>(httpErrorCode ?: 500, "".toResponseBody(null))
            )
        }
        return recipes
    }

    override suspend fun getRandomRecipes(): GetRandomRecipeResult {
        if (shouldReturnError) {
            if (httpErrorCode == 0) {
                throw IOException()
            }
            throw HttpException(
                Response.error<String>(httpErrorCode ?: 500, "".toResponseBody(null))
            )
        }
        return randomRecipes
    }

    override suspend fun getAutocompleteRecipes(query: String): ArrayList<GetRecipesAutocompleteResultItem>  {
        if (shouldReturnError) {
            if (httpErrorCode == 0) {
                throw IOException()
            }
            throw HttpException(
                Response.error<String>(httpErrorCode ?: 500, "".toResponseBody(null))
            )
        }
        return autocompleteResults
    }
}