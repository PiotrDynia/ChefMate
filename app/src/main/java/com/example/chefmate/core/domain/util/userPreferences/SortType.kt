package com.example.chefmate.core.domain.util.userPreferences

enum class SortType(val sortName: String) {
    POPULARITY("popularity"),
    HEALTHINESS("healthiness"),
    PRICE("price"),
    TIME("time"),
    RANDOM("random");

    companion object {
        fun getAllSortTypes() : List<String> = SortType.entries.map { it.sortName }
    }
}