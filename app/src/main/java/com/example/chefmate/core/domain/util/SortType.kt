package com.example.chefmate.core.domain.util

enum class SortType(val sortName: String) {
    POPULARITY("popularity"),
    HEALTHINESS("healthiness"),
    PRICE("price"),
    TIME("time"),
    RANDOM("random")
}

fun getAllSortTypes() : List<String> = SortType.entries.map { it.sortName }