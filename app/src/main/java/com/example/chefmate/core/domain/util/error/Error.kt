package com.example.chefmate.core.domain.util.error

sealed interface Error {
    fun getErrorMessageResId(): Int
}