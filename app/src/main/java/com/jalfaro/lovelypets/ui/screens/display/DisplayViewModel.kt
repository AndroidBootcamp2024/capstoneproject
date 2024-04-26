package com.jalfaro.lovelypets.ui.screens.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalfaro.lovelypets.models.Pet
import com.jalfaro.lovelypets.models.Race
import com.jalfaro.lovelypets.repositories.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayViewModel @Inject constructor(
    private val repository: PetRepository
): ViewModel() {
    private val _pets =  MutableStateFlow<List<Pet>>(emptyList())
    val pets: StateFlow<List<Pet>> = _pets.asStateFlow()

    suspend fun fetchPets() {
        val petList = repository.getPets()
        _pets.value = petList
    }

    fun deletePet(pet: Pet) {
        viewModelScope.launch {
            _pets.value = _pets.value.filter { pet.id != it.id }
            repository.deletePet(pet)
        }
    }
}