package com.example.chefmate.featureOnboarding.domain.util

import androidx.annotation.DrawableRes
import com.example.chefmate.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
//    data object First : OnBoardingPage(
//        image = R.drawable.first,
//        title = "Meeting",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
//    )
//
//    data object Second : OnBoardingPage(
//        image = R.drawable.second,
//        title = "Coordination",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
//    )
//
//    data object Third : OnBoardingPage(
//        image = R.drawable.third,
//        title = "Dialogue",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
//    )
}