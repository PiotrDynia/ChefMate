package com.example.chefmate.featureDetails.presentation

sealed class DetailsEvent {
    data object OnBackClick : DetailsEvent()
    data object OnBookmarkClick : DetailsEvent()
}