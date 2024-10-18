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
            // TODO figure out the logic here, we can't delete ingredients if they are in the shopping cart, flag won't work
            if (!ingredient.isShoppingItem) {
                imageManager.deleteImageFile(ingredient.imagePath)
            }
        }
        return repository.deleteRecipeById(id)
    }
}