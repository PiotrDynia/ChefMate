package com.example.chefmate.core.domain.util

import androidx.annotation.DrawableRes
import com.example.chefmate.R
import java.util.EnumMap

enum class Cuisine(val displayName: String, @DrawableRes val imageResId: Int) {
    AFRICAN("African", R.drawable.cuisine_african),
    ASIAN("Asian", R.drawable.cuisine_asian),
    AMERICAN("American", R.drawable.cuisine_american),
    BRITISH("British", R.drawable.cuisine_british),
    CAJUN("Cajun", R.drawable.cuisine_cajun),
    CARIBBEAN("Caribbean", R.drawable.cuisine_caribbean),
    CHINESE("Chinese", R.drawable.cuisine_chinese),
    EASTERN_EUROPEAN("Eastern european", R.drawable.cuisine_eastern_european),
    EUROPEAN("European", R.drawable.cuisine_european),
    FRENCH("French", R.drawable.cuisine_french),
    GERMAN("German", R.drawable.cuisine_german),
    GREEK("Greek", R.drawable.cuisine_greek),
    INDIAN("Indian", R.drawable.cuisine_indian),
    IRISH("Irish", R.drawable.cuisine_irish),
    ITALIAN("Italian", R.drawable.cuisine_italian),
    JAPANESE("Japanese", R.drawable.cuisine_japanese),
    JEWISH("Jewish", R.drawable.cuisine_jewish),
    KOREAN("Korean", R.drawable.cuisine_korean),
    LATIN_AMERICAN("Latin american", R.drawable.cuisine_latin_american),
    MEDITERRANEAN("Mediterranean", R.drawable.cuisine_mediterranean),
    MEXICAN("Mexican", R.drawable.cuisine_mexican),
    MIDDLE_EASTERN("Middle eastern", R.drawable.cuisine_middle_eastern),
    NORDIC("Nordic", R.drawable.cuisine_nordic),
    SOUTHERN("Southern", R.drawable.cuisine_southern),
    SPANISH("Spanish", R.drawable.cuisine_spanish),
    THAI("Thai", R.drawable.cuisine_thai),
    VIETNAMESE("Vietnamese", R.drawable.cuisine_vietnamese);
}

fun getAllCuisineNames() : List<String> = Cuisine.entries.map { it.displayName }

fun getCuisineMap(): Map<String, Int> {
    return Cuisine.entries.associate { cuisine ->
        cuisine.displayName to cuisine.imageResId
    }
}