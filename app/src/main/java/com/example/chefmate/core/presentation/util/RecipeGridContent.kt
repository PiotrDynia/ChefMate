package com.example.chefmate.core.presentation.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.textFormatter.TextFormatter

@Composable
fun <T> RecipeGridContent(
    results: List<T>,
    getImagePath: (T) -> String,
    getTitle: (T) -> String,
    getSummary: (T) -> String,
    onRecipeClick: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    if (results.isEmpty()) {
        Text(
            text = stringResource(R.string.can_t_find_any_results_try_changing_your_filters),
            fontStyle = FontStyle.Italic
        )
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier.padding(bottom = 100.dp)
        ) {
            items(results) { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .animateItem()
                        .clickable { onRecipeClick(item) }
                ) {
                    AsyncImage(
                        model = getImagePath(item),
                        contentDescription = stringResource(R.string.recipe_image),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.loading_image),
                        error = painterResource(R.drawable.placeholder_image),
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = getTitle(item),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.height(48.dp)
                    )
                    Text(
                        text = TextFormatter.getCleanText(getSummary(item)),
                        textAlign = TextAlign.Justify,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}