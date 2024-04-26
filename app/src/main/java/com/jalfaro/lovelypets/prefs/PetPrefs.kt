package com.jalfaro.lovelypets.prefs

import kotlinx.coroutines.flow.Flow

interface PetPrefs {
    fun getDarkModeEnabled(): Flow<Boolean>
    fun getAllowDeleteEnabled(): Flow<Boolean>
    fun getRotationEnabled(): Flow<Boolean>

    suspend fun toggleDarkMode()
    suspend fun toggleAllowDelete()
    suspend fun toggleRotation()
}