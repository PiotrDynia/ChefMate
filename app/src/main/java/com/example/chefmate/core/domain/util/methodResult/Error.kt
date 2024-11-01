package com.example.chefmate.core.domain.util.methodResult

sealed interface Error {
    fun getErrorMessageResId(): Int
}