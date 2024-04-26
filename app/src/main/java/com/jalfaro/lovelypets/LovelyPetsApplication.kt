package com.jalfaro.lovelypets

import android.app.Application
import com.jalfaro.lovelypets.database.PetDatabase
import com.jalfaro.lovelypets.models.Race
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class LovelyPetsApplication: Application() {}