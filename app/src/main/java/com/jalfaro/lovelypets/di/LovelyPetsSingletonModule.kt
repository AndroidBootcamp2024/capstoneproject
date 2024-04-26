package com.jalfaro.lovelypets.di

import android.content.Context
import com.jalfaro.lovelypets.database.PetDatabase
import com.jalfaro.lovelypets.prefs.PetPrefs
import com.jalfaro.lovelypets.prefs.PetPrefsImpl
import com.jalfaro.lovelypets.repositories.PetRepository
import com.jalfaro.lovelypets.repositories.PetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LovelyPetsSingletonModule {
    @Provides
    @Singleton
    fun providePetDatabase(@ApplicationContext applicationContext: Context): PetDatabase {
        return PetDatabase.buildDatabase(applicationContext)
    }

    @Provides
    @Singleton
    fun providePetPrefs(@ApplicationContext applicationContext: Context): PetPrefs {
        return PetPrefsImpl(applicationContext)
    }

    @Provides
    @Singleton
    fun providesPetRepository(
        database: PetDatabase,
        prefs: PetPrefs,
    ): PetRepository = PetRepositoryImpl(database.petDao(), database.raceDao(), prefs)
}