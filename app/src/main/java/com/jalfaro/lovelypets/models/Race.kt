package com.jalfaro.lovelypets.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "race")
data class Race (
    @PrimaryKey
    val id: Int,
    val name: String
): Parcelable