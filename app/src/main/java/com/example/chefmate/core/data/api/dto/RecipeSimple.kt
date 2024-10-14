package com.example.chefmate.core.data.api.dto

import org.jsoup.Jsoup

data class RecipeSimple(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String
) {
    fun getCleanSummary(): String {
        return Jsoup.parse(summary).text()
    }
}