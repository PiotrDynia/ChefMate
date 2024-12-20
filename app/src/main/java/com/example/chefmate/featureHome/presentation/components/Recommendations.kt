package com.example.chefmate.featureHome.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import com.example.chefmate.featureHome.presentation.HomeState

@Composable
fun RecommendationsRow(
    state: HomeState,
    onSeeAllClick: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.recommendations),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = stringResource(R.string.see_all),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
                    .then(
                        if (state.recommendations.isNotEmpty()) Modifier.clickable { onSeeAllClick() }
                        else Modifier
                    ),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    color = if (state.recommendations.isNotEmpty()) MaterialTheme.colorScheme.tertiary
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            )
        }
        if (state.errorMessageResId != null) {
            Text(
                text = stringResource(state.errorMessageResId),
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(state.recommendations) { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .animateItem()
                            .clickable {
                                onRecipeClick(item.id)
                            }
                    ) {
                        AsyncImage(
                            model = item.image,
                            contentDescription = stringResource(R.string.recipe_image),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.loading_image),
                            error = painterResource(R.drawable.placeholder_image),
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .aspectRatio(3f / 4f)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .width(120.dp),
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}