package com.example.chefmate.core.data.api

import com.example.chefmate.BuildConfig
import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem
import com.example.chefmate.core.domain.util.SortType
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("query") query: String = "",
        @Query("sort") sortStrategy: String = SortType.POPULARITY.sortName,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("cuisine") cuisines: String = "",
        @Query("excludeCuisine") excludedCuisines: String = "",
        @Query("diet") diets: String = "",
        @Query("intolerances") intolerances: String = "",
        @Query("type") mealTypes: String = "",
        @Query("minServings") minServings: Int = 0,
        @Query("maxServings") maxServings: Int = Int.MAX_VALUE,
        @Query("number") resultsCount: Int = 10,
        @Query("minCalories") minCalories: Int = 0,
        @Query("maxCalories") maxCalories: Int = Int.MAX_VALUE,
        @Query("apiKey") apiKey: String = API_KEY
    ) : GetRecipeResult

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") resultsCount: Int = 10,
        @Query("apiKey") apiKey: String = API_KEY
    ) : GetRandomRecipeResult

    @GET("recipes/autocomplete")
    suspend fun getAutocompleteRecipes(
        @Query("query") query: String = "",
        @Query("number") resultsCount: Int = 5,
        @Query("apiKey") apiKey: String = API_KEY
    ) : ArrayList<GetRecipesAutocompleteResultItem>
}