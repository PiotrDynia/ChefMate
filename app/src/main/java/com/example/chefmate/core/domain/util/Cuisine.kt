package com.example.chefmate.core.domain.util

enum class Cuisine(val displayName: String) {
    AFRICAN("african"),
    ASIAN("asian"),
    AMERICAN("american"),
    BRITISH("british"),
    CAJUN("cajun"),
    CARIBBEAN("caribbean"),
    CHINESE("chinese"),
    EASTERN_EUROPEAN("eastern european"),
    EUROPEAN("european"),
    FRENCH("french"),
    GERMAN("german"),
    GREEK("greek"),
    INDIAN("indian"),
    IRISH("irish"),
    ITALIAN("italian"),
    JAPANESE("japanese"),
    JEWISH("jewish"),
    KOREAN("korean"),
    LATIN_AMERICAN("latin american"),
    MEDITERRANEAN("mediterranean"),
    MEXICAN("mexican"),
    MIDDLE_EASTERN("middle eastern"),
    NORDIC("nordic"),
    SOUTHERN("southern"),
    SPANISH("spanish"),
    THAI("thai"),
    VIETNAMESE("vietnamese");
}

fun getAllCuisineNames() : List<String> = Cuisine.entries.map { it.displayName }