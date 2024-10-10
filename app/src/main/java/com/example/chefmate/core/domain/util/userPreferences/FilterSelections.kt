package com.example.chefmate.core.domain.util.userPreferences

import androidx.annotation.StringRes
import com.example.chefmate.R

enum class FilterSelections(@StringRes val displayNameResId: Int) {
    CUISINE(R.string.cuisine),
    EXCLUDED_CUISINE(R.string.excluded_cuisine),
    DIET(R.string.diet),
    INTOLERANCE(R.string.intolerance),
    MEAL_TYPE(R.string.meal_type),
    SORT(R.string.sort_lowercase),
    CALORIES(R.string.calories),
    SERVINGS(R.string.servings_lowercase);

    companion object {
        fun getAllFilterSelectionNames() : List<Int> = FilterSelections.entries.map { it.displayNameResId }
    }
}

