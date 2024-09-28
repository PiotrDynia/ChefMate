package com.example.chefmate.core.data.api

import com.example.chefmate.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("query") query: String = "",
        @Query("sort") sortStrategy: String = "popularity",
        @Query("cuisine") cuisines: String = "",
        @Query("diet") diets: String = "",
        @Query("intolerances") intolerances: String = "",
        @Query("number") resultsCount: Int = 10,
        @Query("apiKey") apiKey: String = API_KEY
    ) : GetRecipeResult

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") resultsCount: Int = 10,
        @Query("apiKey") apiKey: String = API_KEY
    ) : GetRandomRecipeResult
}