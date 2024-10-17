package com.example.chefmate.core.domain.util

import org.jsoup.Jsoup

class TextFormatter {
    companion object {
        fun getCleanText(value: String): String {
            return Jsoup.parse(value).text()
        }
    }
}