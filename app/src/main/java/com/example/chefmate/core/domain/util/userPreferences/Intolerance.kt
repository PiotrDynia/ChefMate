package com.example.chefmate.core.domain.util.userPreferences

import androidx.annotation.DrawableRes
import com.example.chefmate.R

enum class Intolerance(val displayName: String, @DrawableRes val imageResId: Int) {
    DAIRY("Dairy", R.drawable.intolerance_dairy),
    EGG("Egg", R.drawable.intolerance_egg),
    GLUTEN("Gluten", R.drawable.intolerance_gluten),
    GRAIN("Grain", R.drawable.intolerance_grain),
    PEANUT("Peanut", R.drawable.intolerance_peanut),
    SEAFOOD("Seafood", R.drawable.intolerance_seafood),
    SESAME("Sesame", R.drawable.intolerance_sesame),
    SHELLFISH("Shellfish", R.drawable.intolerance_shellfish),
    SOY("Soy", R.drawable.intolerance_soy),
    SULFITE("Sulfite", R.drawable.intolerance_sulfite),
    TREE_NUT("Tree nut", R.drawable.intolerance_tree_nut),
    WHEAT("Wheat", R.drawable.intolerance_wheat);

    companion object {
        fun getAllIntoleranceNames() : List<String> = Intolerance.entries.map { it.displayName }

    }
}