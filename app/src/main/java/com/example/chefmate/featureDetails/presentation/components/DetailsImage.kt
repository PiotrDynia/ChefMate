package com.example.chefmate.featureDetails.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.chefmate.R
import com.example.chefmate.featureDetails.presentation.DetailsEvent
import com.example.chefmate.featureDetails.presentation.DetailsState
import java.io.File

@Composable
fun DetailsImage(
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageModel = state.details?.image?.let { imagePath ->
        if (imagePath.startsWith("/data/")) {
            File(imagePath)
        } else {
            imagePath
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = imageModel,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.loading_image),
            error = painterResource(R.drawable.placeholder_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        IconButton(
            onClick = { onEvent(DetailsEvent.OnBackClick) },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        IconButton(
            onClick = { onEvent(DetailsEvent.OnBookmarkClick)},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
                .size(40.dp)
        ) {
            Icon(
                imageVector = if(state.isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                contentDescription = stringResource(R.string.bookmark),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}