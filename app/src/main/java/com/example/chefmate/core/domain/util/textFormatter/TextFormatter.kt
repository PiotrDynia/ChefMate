package com.example.chefmate.core.domain.util.textFormatter

import org.jsoup.Jsoup

class TextFormatter {
    companion object {
        fun getCleanText(value: String): String {
            return Jsoup.parse(value).text()
        }
    }
}