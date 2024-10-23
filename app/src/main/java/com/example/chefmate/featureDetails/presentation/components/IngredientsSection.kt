package com.example.chefmate.featureDetails.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.chefmate.R
import com.example.chefmate.core.data.api.dto.Ingredient
import java.io.File

@Composable
fun IngredientsSection(ingredients: List<Ingredient>?, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.ingredients),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = stringResource(R.string.add_all),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                /* TODO add to shopping list */
            }
        )
    }
    Text(
        text = stringResource(R.string.press_to_add_the_ingredient_to_shopping_list),
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp
    )
    ingredients?.forEach { ingredient ->
        val imageModel = ingredient.image.let { imagePath ->
            if (imagePath.startsWith("/data/")) {
                File(imagePath)
            } else {
                "https://spoonacular.com/cdn/ingredients_100x100/${imagePath}"
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageModel,
                contentDescription = null,
                placeholder = painterResource(R.drawable.loading_image),
                error = painterResource(R.drawable.placeholder_image),
                modifier = Modifier
                    .size(40.dp)
                    .background(color = MaterialTheme.colorScheme.background)
                    .clip(RectangleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = ingredient.original,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { /* TODO add to shopping list */ },
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = stringResource(R.string.add_to_shopping_list),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}