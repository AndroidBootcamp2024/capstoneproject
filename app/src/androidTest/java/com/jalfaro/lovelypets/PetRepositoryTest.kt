package com.jalfaro.lovelypets

import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jalfaro.lovelypets.database.PetDatabase
import com.jalfaro.lovelypets.models.Pet
import com.jalfaro.lovelypets.models.Race

import com.jalfaro.lovelypets.prefs.PetPrefsImpl
import com.jalfaro.lovelypets.repositories.PetRepositoryImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PetRepositoryTest {

    private lateinit var petRepository: PetRepositoryImpl
    private lateinit var database: PetDatabase

    @Before
    fun setUp() {
        // Create an in-memory Room database for testing
        database = inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PetDatabase::class.java
        ).allowMainThreadQueries().build()

        // Initialize PetRepositoryImpl with the in-memory Room database
        petRepository = PetRepositoryImpl(
            database.petDao(),
            database.raceDao(),
            PetPrefsImpl(ApplicationProvider.getApplicationContext())
        )

        // Insert some test data into the in-memory database
        runBlocking {
            database.raceDao().insertAll(
                Race(1, "Dog"),
                Race(2, "Cat"),
                Race(3, "Bird"),
                Race(4, "Fish")
            )
            database.petDao().addPet(Pet(1, "Fluffy", "Dog", "image1.jpg"))
        }
    }

    @After
    fun tearDown() {
        // Close the in-memory Room database after each test
        database.close()
    }

    @Test
    fun testGetRaces() {
        val races = runBlocking {
            petRepository.getRaces()
        }

        assertEquals(4, races.size)
    }

    @Test
    fun testGetPets() {
        val pets = runBlocking {
            petRepository.getPets()
        }
        assertEquals(1, pets.size)
    }
}

