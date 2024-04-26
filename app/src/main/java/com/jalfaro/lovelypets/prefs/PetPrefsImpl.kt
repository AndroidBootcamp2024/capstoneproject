package com.jalfaro.lovelypets.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val STORE_NAME = "pets_prefs"

class PetPrefsImpl @Inject constructor(@ApplicationContext context: Context) : PetPrefs {
    private val Context.dataStore by preferencesDataStore(name = STORE_NAME)
    private val dataStore = context.dataStore

    override fun getDarkModeEnabled(): Flow<Boolean> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[STORE_KEY_DARK_MODE] ?: false
    }

    override fun getAllowDeleteEnabled(): Flow<Boolean> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[STORE_KEY_ALLOW_DELETE] ?: false
    }

    override fun getRotationEnabled(): Flow<Boolean> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[STORE_KEY_ROTATION] ?: false
    }

    override suspend fun toggleDarkMode() {
        dataStore.edit {
            it[STORE_KEY_DARK_MODE] = it[STORE_KEY_DARK_MODE]?.not() ?: false
        }
    }

    override suspend fun toggleAllowDelete() {
        dataStore.edit {
            it[STORE_KEY_ALLOW_DELETE] = it[STORE_KEY_ALLOW_DELETE]?.not() ?: false
        }
    }

    override suspend fun toggleRotation() {
        dataStore.edit {
            it[STORE_KEY_ROTATION] = it[STORE_KEY_ROTATION]?.not() ?: false
        }
    }

    companion object {
        private val STORE_KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        private val STORE_KEY_ALLOW_DELETE = booleanPreferencesKey("allow_delete")
        private val STORE_KEY_ROTATION = booleanPreferencesKey("rotation")
    }
}