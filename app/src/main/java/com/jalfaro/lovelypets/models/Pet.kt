package com.jalfaro.lovelypets.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="pet")
data class Pet (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val race: String,
    val picture: String
): Parcelable