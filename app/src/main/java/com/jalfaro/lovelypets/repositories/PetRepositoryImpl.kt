package com.jalfaro.lovelypets.repositories

import com.jalfaro.lovelypets.database.PetDao
import com.jalfaro.lovelypets.database.RaceDao
import com.jalfaro.lovelypets.models.Pet
import com.jalfaro.lovelypets.models.Race
import com.jalfaro.lovelypets.prefs.PetPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PetRepositoryImpl(
    private val petDao: PetDao,
    private val raceDao: RaceDao,
    private val petPrefs: PetPrefs
): PetRepository {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val _pets: MutableStateFlow<List<Pet>> = MutableStateFlow(emptyList())
    override val pets: StateFlow<List<Pet>> = _pets.asStateFlow()
    private val _races: MutableStateFlow<List<Race>> = MutableStateFlow(emptyList())
    override val races: StateFlow<List<Race>> = _races.asStateFlow()

    init {
        coroutineScope.launch {
            if (raceDao.getAllRaces().isEmpty()) {
                raceDao.insertAll(
                    Race(1, "Dog"),
                    Race(2, "Cat"),
                    Race(3, "Bird"),
                    Race(4, "Fish")
                )
            }
        }
    }

    override suspend fun getRaces(): List<Race> {
        return raceDao.getAllRaces()
    }

    override suspend fun getPets(): List<Pet> {
        return petDao.getAllPets()
    }

    override suspend fun insertPet(pet: Pet) {
        coroutineScope.launch {
            petDao.addPet(pet)
        }
    }

    override suspend fun deletePet(pet: Pet) {
        coroutineScope.launch {
            petDao.deletePet(pet)
        }
        return
    }

    override fun getPet(petId: Int): Pet? {
        var pet: Pet? = null
        coroutineScope.launch {
            pet = petDao.getPet(petId)
        }
        return pet
    }

    override fun getRace(raceId: Int): Race? {
        var race: Race? = null
        coroutineScope.launch {
            race = raceDao.getRace(raceId)
        }
        return race
    }

}