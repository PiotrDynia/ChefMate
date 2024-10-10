package com.example.chefmate.featureSearch.data.repository

import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.featureSearch.domain.repository.SearchRepository
import com.example.chefmate.featureSearch.domain.util.SearchFilterSelection
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class FakeSearchRepository : SearchRepository {
    var shouldReturnError = false
    var httpErrorCode: Int? = null
    var recipes: GetRecipeResult = GetRecipeResult(emptyList())
    
    override suspend fun getRecipes(searchFilters: SearchFilterSelection): GetRecipeResult {
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
}