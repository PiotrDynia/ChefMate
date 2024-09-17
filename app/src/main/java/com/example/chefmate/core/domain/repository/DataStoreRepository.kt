package com.example.chefmate.core.domain.repository

import com.example.chefmate.core.domain.util.DietPreferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState() : Flow<Boolean>
    suspend fun saveDietPreferences(dietPreferences: DietPreferences)
    fun getDietPreferences() : Flow<DietPreferences>
}