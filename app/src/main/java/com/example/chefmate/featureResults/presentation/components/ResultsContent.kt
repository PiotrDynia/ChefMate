package com.example.chefmate.featureResults.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.chefmate.core.data.api.dto.RecipeSimple
import com.example.chefmate.core.presentation.util.RecipeGridContent

@Composable
fun ResultsContent(
    results: List<RecipeSimple>,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    RecipeGridContent(
        results = results,
        getImagePath = { it.image },
        getTitle = { it.title },
        getSummary = { it.summary },
        onRecipeClick = { onRecipeClick(it.id) }
    )
}