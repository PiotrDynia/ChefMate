package com.example.chefmate.featureHome.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chefmate.featureHome.presentation.components.SearchSection
import com.example.chefmate.featureHome.presentation.components.TopWelcomeRow
import com.example.chefmate.featureOnboarding.presentation.WelcomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TopWelcomeRow(modifier = modifier)
        SearchSection(modifier = modifier)

//        Button(onClick = { viewModel.saveOnBoardingState(false) }) {
//
//        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}