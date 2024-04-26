package com.jalfaro.lovelypets.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jalfaro.lovelypets.models.Pet
import com.jalfaro.lovelypets.models.Race



private const val DATABASE_NAME = "country_database"
private const val DATABASE_VERSION = 1

@Database(
    entities = [Pet::class, Race::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class PetDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun raceDao(): RaceDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            PetDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}