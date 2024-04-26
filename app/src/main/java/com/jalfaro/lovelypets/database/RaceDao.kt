package com.jalfaro.lovelypets.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jalfaro.lovelypets.models.Race

@Dao
interface RaceDao {

    @Query("SELECT * FROM race")
    suspend fun getAllRaces(): List<Race>

    @Query("SELECT * FROM race WHERE id = :id")
    suspend fun getRace(id: Int) : Race

    @Insert
    suspend fun insertAll(vararg race: Race)
}