package com.example.chefmate.featureDetails.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.chefmate.R
import com.example.chefmate.core.domain.util.textFormatter.TextFormatter

@Composable
fun SummaryText(
    summary: String?,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.summary),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    Text(
        text = TextFormatter.getCleanText(summary ?: ""),
        textAlign = TextAlign.Justify
    )
}