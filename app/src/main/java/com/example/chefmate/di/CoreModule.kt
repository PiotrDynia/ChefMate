package com.example.chefmate.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.db.ChefMateDatabase
import com.example.chefmate.core.data.repository.DataStoreRepositoryImpl
import com.example.chefmate.core.domain.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) : DataStoreRepository {
        return DataStoreRepositoryImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : ChefMateDatabase {
        return Room.databaseBuilder(
            app,
            ChefMateDatabase::class.java,
            "chefmate_db"
        )
            .build()
    }

    @Provides
    fun provideBaseUrl(): String = "https://api.spoonacular.com/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit) : APIService {
        return retrofit.create(APIService::class.java)
    }
}