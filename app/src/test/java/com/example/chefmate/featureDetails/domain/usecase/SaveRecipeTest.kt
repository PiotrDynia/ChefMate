package com.example.chefmate.featureDetails.domain.usecase

import com.example.chefmate.core.domain.util.FakeImageManager
import com.example.chefmate.featureDetails.data.repository.FakeDetailsRepository
import org.junit.Assert.*
import org.junit.Before

class SaveRecipeTest {
    private lateinit var repository: FakeDetailsRepository
    private lateinit var imageManager: FakeImageManager
    private lateinit var saveRecipe: SaveRecipe

    @Before
    fun setUp() {
        repository = FakeDetailsRepository()
        imageManager = FakeImageManager()
        saveRecipe = SaveRecipe(repository, imageManager)
    }
}