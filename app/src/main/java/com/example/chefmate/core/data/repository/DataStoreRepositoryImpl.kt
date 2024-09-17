package com.example.chefmate.core.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.chefmate.core.domain.repository.DataStoreRepository
import com.example.chefmate.core.domain.util.DietPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class DataStoreRepositoryImpl(context: Context) : DataStoreRepository {

    companion object {
        val ON_BOARDING_KEY = booleanPreferencesKey(name = "on_boarding_completed")
        val DIET_PREFERENCES_KEY = stringPreferencesKey(name = "diet_preferences")
        val CUISINE_PREFERENCES_KEY = stringPreferencesKey(name = "cuisine_preferences")
        val INTOLERANCES_KEY = stringPreferencesKey(name = "intolerances")
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[ON_BOARDING_KEY] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[ON_BOARDING_KEY] ?: false
                onBoardingState
            }
    }

    override suspend fun saveDietPreferences(dietPreferences: DietPreferences) {
        dataStore.edit { preferences ->
            preferences[DIET_PREFERENCES_KEY] = dietPreferences.diets.joinToString(",")
            preferences[CUISINE_PREFERENCES_KEY] = dietPreferences.cuisines.joinToString(",")
            preferences[INTOLERANCES_KEY] = dietPreferences.intolerances.joinToString(",")
        }
    }

    override fun getDietPreferences(): Flow<DietPreferences> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val diets = preferences[DIET_PREFERENCES_KEY]?.split(",") ?: emptyList()
                val cuisines = preferences[CUISINE_PREFERENCES_KEY]?.split(",") ?: emptyList()
                val intolerances = preferences[INTOLERANCES_KEY]?.split(",") ?: emptyList()
                DietPreferences(diets, cuisines, intolerances)
            }
    }
}