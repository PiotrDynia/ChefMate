package com.example.chefmate.featureBookmarks.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.chefmate.core.presentation.util.RecipeGridContent
import com.example.chefmate.featureDetails.domain.model.RecipeEntity

@Composable
fun BookmarksContent(
    results: List<RecipeEntity>,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    RecipeGridContent(
        results = results,
        getImagePath = { it.imagePath },
        getTitle = { it.title },
        getSummary = { it.summary },
        onRecipeClick = { onRecipeClick(it.id) }
    )
}