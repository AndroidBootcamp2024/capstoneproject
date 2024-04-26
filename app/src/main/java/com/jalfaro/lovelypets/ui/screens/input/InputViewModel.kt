package com.jalfaro.lovelypets.ui.screens.input


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jalfaro.lovelypets.models.Pet
import com.jalfaro.lovelypets.models.Race
import com.jalfaro.lovelypets.repositories.PetRepository
import com.jalfaro.lovelypets.ui.screens.display.PetDisplayState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val repository: PetRepository,
): ViewModel() {
    private val _petName = MutableStateFlow("")
    val petName: StateFlow<String> = _petName.asStateFlow()

    private val _raceSelected = MutableStateFlow(Race(0,""))
    val raceSelected: StateFlow<Race> = _raceSelected.asStateFlow()

    private val _picture = MutableStateFlow(Uri.EMPTY)
    val picture = _picture.asStateFlow()

    private val _races = MutableStateFlow<List<Race>>(emptyList())
    val races: StateFlow<List<Race>> = _races.asStateFlow()


    suspend fun loadRaces() {
        val racesFromDB = repository.getRaces()
        _races.value = racesFromDB
    }


    fun setRace(race: Race) {
        _raceSelected.value = race
    }

    fun setPetName(name: String) {
        _petName.value = name
    }

    fun setPicture(picture: Uri) {
        _picture.value = picture
    }

    fun savePet() {
        viewModelScope.launch {
            val pet = Pet(name= petName.value, race = raceSelected.value.name, picture =   picture.value.toString() ?: "")
            repository.insertPet(pet)
        }
    }

}

