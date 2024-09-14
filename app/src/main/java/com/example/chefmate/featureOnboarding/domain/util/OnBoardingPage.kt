package com.example.chefmate.featureOnboarding.domain.util

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.chefmate.R

sealed class OnBoardingPage(
    @RawRes
    val animation: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
) {
    data object First : OnBoardingPage(
        animation = R.raw.welcome_animation,
        title = R.string.chefmate,
        description = R.string.welcome_to_chefmate_the_app_that_lets_you_do_more_with_recipes_and_nutrition_than_you_ever_thought_possible
    )

    data object Second : OnBoardingPage(
        animation = R.raw.browse_animation,
        title = R.string.browse_recipes,
        description = R.string.explore_hundreds_of_recipes_filter_by_categories_and_add_your_favorites
    )

    data object Third : OnBoardingPage(
        animation = R.raw.shopping_list_animation,
        title = R.string.shopping_list,
        description = R.string.create_a_shopping_list_from_recipe_ingredients_customize_it_or_combine_ingredients_from_multiple_recipes
    )

    data object Fourth : OnBoardingPage(
        animation = R.raw.virtual_assistant_animation,
        title = R.string.virtual_assistant,
        description = R.string.chat_with_our_virtual_assistant_for_inspiration_or_to_get_answers_to_your_questions
    )

    data object Fifth : OnBoardingPage(
        animation = R.raw.dietary_animation,
        title = R.string.dietary_preferences,
        description = R.string.set_your_dietary_preferences_to_find_recipes_that_suit_you_better_you_can_change_them_anytime_in_the_settings
    )

    companion object {
        const val LAST_SCREEN_INDEX = 4
        const val PAGES_COUNT = 5
    }
}