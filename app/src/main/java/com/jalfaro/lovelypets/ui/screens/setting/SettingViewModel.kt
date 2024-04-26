package com.jalfaro.lovelypets.ui.screens.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalfaro.lovelypets.prefs.PetPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val prefs: PetPrefs): ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            prefs.getDarkModeEnabled()
                .collect {
                    _uiState.value = _uiState.value.copy(darkMode = it)
                }
        }
        viewModelScope.launch {
            prefs.getAllowDeleteEnabled()
                .collect {
                    _uiState.value = _uiState.value.copy(deleteEnabled = it)
                }
        }

        viewModelScope.launch {
            prefs.getRotationEnabled()
                .collect {
                    _uiState.value = _uiState.value.copy(rotationEnabled = it)
                }
        }
    }

    fun toggleDarkMode() {
        viewModelScope.launch {
            prefs.toggleDarkMode()
        }
    }

    fun toggleAllowDelete() {
        viewModelScope.launch {
            prefs.toggleAllowDelete()
        }
    }

    fun toggleRotation() {
        viewModelScope.launch {
            prefs.toggleRotation()
        }
    }
}