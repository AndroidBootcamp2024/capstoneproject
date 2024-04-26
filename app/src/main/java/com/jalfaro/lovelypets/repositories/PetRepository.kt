package com.jalfaro.lovelypets.repositories

import com.jalfaro.lovelypets.models.Pet
import com.jalfaro.lovelypets.models.Race
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    val pets: Flow<List<Pet>>
    val races : Flow<List<Race>>

    suspend fun getRaces(): List<Race>
    suspend fun getPets(): List<Pet>
    suspend fun insertPet(pet: Pet)
    suspend fun deletePet(pet: Pet)
    fun getPet(petId: Int): Pet?
    fun getRace(raceId: Int): Race?
}