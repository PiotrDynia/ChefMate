package com.example.chefmate.featureHome.domain.usecase

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResult

class GetAutocompleteRecipes(
    private val apiService: APIService
) {

    suspend operator fun invoke(input: String) : GetRecipesAutocompleteResult {
        // TODO change to Result<GetRandomRecipeResult, DataError.NetworkError>
        return apiService.getAutocompleteRecipes(query = input)
    }
}