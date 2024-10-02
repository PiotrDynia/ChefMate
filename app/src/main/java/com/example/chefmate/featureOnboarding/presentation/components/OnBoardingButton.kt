package com.example.chefmate.featureOnboarding.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.featureOnboarding.domain.util.OnBoardingPage

@Composable
fun OnBoardingButton(
    pagerState: PagerState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonTextResId = when {
        shouldDisplayFinish(pagerState) -> R.string.finish
        shouldDisplaySkip(pagerState) -> R.string.skip
        else -> null
    }

    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = buttonTextResId?.let { stringResource(it) } ?: ""
            )
        }
    }
}

private fun shouldDisplayFinish(pagerState: PagerState): Boolean {
    return pagerState.currentPage == OnBoardingPage.LAST_SCREEN_INDEX
}

private fun shouldDisplaySkip(pagerState: PagerState): Boolean {
    return pagerState.currentPage < OnBoardingPage.LAST_SCREEN_INDEX
}
