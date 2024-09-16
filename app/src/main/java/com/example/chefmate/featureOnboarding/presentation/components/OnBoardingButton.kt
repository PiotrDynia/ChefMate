package com.example.chefmate.featureOnboarding.presentation.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chefmate.R
import com.example.chefmate.featureOnboarding.domain.util.OnBoardingPage

enum class ButtonType {
    FINISH,
    SKIP
}

@Composable
fun OnBoardingButton(
    pagerState: PagerState,
    buttonType: ButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isVisible = when (buttonType) {
        ButtonType.FINISH -> isFinishVisible(pagerState)
        ButtonType.SKIP -> isSkipVisible(pagerState)
    }

    val buttonTextResId = when (buttonType) {
        ButtonType.FINISH -> R.string.finish
        ButtonType.SKIP -> R.string.skip
    }

    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isVisible
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(buttonTextResId)
                )
            }
        }
    }
}

private fun isFinishVisible(pagerState: PagerState): Boolean {
    return pagerState.currentPage == OnBoardingPage.LAST_SCREEN_INDEX
}

private fun isSkipVisible(pagerState: PagerState): Boolean {
    return pagerState.currentPage < OnBoardingPage.LAST_SCREEN_INDEX
}
