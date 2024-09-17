package com.example.chefmate.core.domain.util

enum class Intolerance(val displayName: String) {
    DAIRY("dairy"),
    EGG("egg"),
    GLUTEN("gluten"),
    GRAIN("grain"),
    PEANUT("peanut"),
    SEAFOOD("seafood"),
    SESAME("sesame"),
    SHELLFISH("shellfish"),
    SOY("soy"),
    SULFITE("sulfite"),
    TREE_NUT("tree nut"),
    WHEAT("wheat");
}

fun getAllIntoleranceNames() : List<String> = Intolerance.entries.map { it.displayName }