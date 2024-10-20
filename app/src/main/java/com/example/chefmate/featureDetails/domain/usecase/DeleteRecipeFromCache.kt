package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.core.domain.util.ImageCacheManager
import com.example.chefmate.featureDetails.domain.repository.DetailsRepository

class DeleteRecipeFromCache(
    private val repository: DetailsRepository,
    private val imageManager: ImageCacheManager
) {

    suspend operator fun invoke(id: Int, imagePath: String) {
        val cachedRecipeWithIngredients = repository.getRecipeFromCache(id)

        imageManager.deleteImageFile(imagePath)

        cachedRecipeWithIngredients.ingredients.forEach { ingredient ->
            if (!ingredient.isInShoppingCart) {
                imageManager.deleteImageFile(ingredient.imagePath)
            }
        }
        return repository.deleteRecipeById(id)
    }
}