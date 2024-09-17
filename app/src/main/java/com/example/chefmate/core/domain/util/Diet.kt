package com.example.chefmate.core.domain.util

enum class Diet(val displayName: String) {
    GLUTEN_FREE("gluten free"),
    KETOGENIC("ketogenic"),
    VEGETARIAN("vegetarian"),
    LACTO_VEGETARIAN("lacto-vegetarian"),
    OVO_VEGETARIAN("ovo-vegetarian"),
    VEGAN("vegan"),
    PESCETARIAN("pescetarian"),
    PALEO("paleo"),
    PRIMAL("primal"),
    LOW_FODMAP("low fodmap"),
    WHOLE30("whole30")
}

fun getAllDietNames() : List<String> = Diet.entries.map { it.displayName }