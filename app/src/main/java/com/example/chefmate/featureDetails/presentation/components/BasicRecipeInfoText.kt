package com.example.chefmate.featureDetails.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RoomService
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chefmate.featureDetails.presentation.DetailsState

@Composable
fun BasicRecipeInfoText(
    state: DetailsState,
    modifier: Modifier = Modifier
) {
    Text(
        text = state.details?.title ?: "",
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Justify,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.ThumbUp,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = state.details?.aggregateLikes.toString(),
                fontStyle = FontStyle.Italic
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.Timer,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${state.details?.readyInMinutes} min",
                fontStyle = FontStyle.Italic
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Outlined.RoomService, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = state.details?.servings.toString(),
                fontStyle = FontStyle.Italic
            )
        }
    }
}