package com.jalfaro.lovelypets.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jalfaro.lovelypets.models.Pet

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPet(pet: Pet)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPets(vararg pet: Pet)

    @Query("SELECT * FROM pet WHERE id = :id")
    suspend fun getPet(id: Int): Pet

    @Query("SELECT * FROM pet")
    suspend fun getAllPets(): List<Pet>

    @Update
    suspend fun updatePet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet)

    @Query("DELETE FROM pet")
    suspend fun deleteAllPets()
}