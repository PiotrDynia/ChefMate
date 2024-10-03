package com.example.chefmate.core.domain.util

import androidx.annotation.DrawableRes
import com.example.chefmate.R

enum class MealType(val displayName: String, @DrawableRes val imageResId: Int) {
    MAIN_COURSE("Main course", R.drawable.meal_type_main_course),
    SIDE_DISH("Side dish", R.drawable.meal_type_side_dish),
    DESSERT("Dessert", R.drawable.meal_type_dessert),
    APPETIZER("Appetizer", R.drawable.meal_type_appetizer),
    SALAD("Salad", R.drawable.meal_type_salad),
    BREAD("Bread", R.drawable.meal_type_bread),
    BREAKFAST("Breakfast", R.drawable.meal_type_breakfast),
    SOUP("Soup", R.drawable.meal_type_soup),
    BEVERAGE("Beverage", R.drawable.meal_type_beverage),
    SAUCE("Sauce", R.drawable.meal_type_sauce),
    MARINADE("Marinade", R.drawable.meal_type_marinade),
    FINGERFOOD("Fingerfood", R.drawable.meal_type_fingerfood),
    SNACK("Snack", R.drawable.meal_type_snack),
    DRINK("Drink", R.drawable.meal_type_drink),
}