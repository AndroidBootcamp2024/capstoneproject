package com.jalfaro.lovelypets.ui.screens.display

import com.jalfaro.lovelypets.models.Pet

sealed class PetDisplayState {
    data object Loading : PetDisplayState()
    data class Success(
        val pets: List<Pet>,
        val isDeleteEnabledSetting: Boolean,
    ) : PetDisplayState()
    data class Error(val error: Throwable) : PetDisplayState()
}