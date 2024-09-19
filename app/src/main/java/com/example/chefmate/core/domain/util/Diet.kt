package com.example.chefmate.core.domain.util

import androidx.annotation.DrawableRes
import com.example.chefmate.R

enum class Diet(val displayName: String, @DrawableRes val imageResId: Int) {
    GLUTEN_FREE("Gluten free", R.drawable.diet_gluten_free),
    KETOGENIC("Ketogenic", R.drawable.diet_ketogenic),
    VEGETARIAN("Vegetarian", R.drawable.diet_vegetarian),
    LACTO_VEGETARIAN("Lacto - vegetarian", R.drawable.diet_lacto_vegetarian),
    OVO_VEGETARIAN("Ovo - vegetarian", R.drawable.diet_ovo_vegetarian),
    VEGAN("Vegan", R.drawable.diet_vegan),
    PESCETARIAN("Pescetarian", R.drawable.diet_pescetarian),
    PALEO("Paleo", R.drawable.diet_paleo),
    PRIMAL("Primal", R.drawable.diet_primal),
    LOW_FODMAP("Low fodmap", R.drawable.diet_low_fodmap),
    WHOLE30("Whole30", R.drawable.diet_whole30)
}

fun getAllDietNames() : List<String> = Diet.entries.map { it.displayName }