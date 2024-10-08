package com.example.chefmate.core.domain.util.error

import com.example.chefmate.R

enum class ValidationError : Error {
    EMPTY,
    TOO_SHORT;

    override fun getErrorMessageResId(): Int {
        return when (this) {
            EMPTY -> R.string.query_cannot_be_empty_please_enter_a_keyword_or_phrase_to_perform_the_search
            TOO_SHORT -> R.string.enter_at_least_3_characters
        }
    }
}