package com.example.chefmate.featureHome.data.repository

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.GetRandomRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipeResult
import com.example.chefmate.core.data.api.dto.GetRecipesAutocompleteResultItem
import com.example.chefmate.featureHome.domain.repository.HomeRepository
import com.example.chefmate.featureHome.domain.util.PreferencesSelection

class HomeRepositoryImpl(private val apiService: APIService) : HomeRepository {

    override suspend fun getRecipes(preferencesSelection: PreferencesSelection): GetRecipeResult {
        return apiService.getRecipes(
            cuisines = preferencesSelection.selectedCuisines.joinToString(separator = ",") { it.displayName },
            diets = preferencesSelection.selectedDiets.joinToString(separator = ",") { it.displayName },
            intolerances = preferencesSelection.selectedIntolerances.joinToString(separator = ",") { it.displayName },
            mealTypes = preferencesSelection.selectedMealTypes.joinToString(separator = ",") { it.displayName }
        )
    }

    override suspend fun getRandomRecipes(): GetRandomRecipeResult {
        return apiService.getRandomRecipes()
    }

    override suspend fun getAutocompleteRecipes(query: String): ArrayList<GetRecipesAutocompleteResultItem> {
        return apiService.getAutocompleteRecipes(query = query)
    }
}