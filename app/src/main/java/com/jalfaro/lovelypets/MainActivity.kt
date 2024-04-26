package com.jalfaro.lovelypets

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.jalfaro.lovelypets.prefs.PetPrefs
import com.jalfaro.lovelypets.ui.nav.LovelyPetsNavHost
import com.jalfaro.lovelypets.ui.theme.LovelyPetsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var prefs: PetPrefs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            prefs.getRotationEnabled()
                .collect { rotationEnabled ->
                    requestedOrientation = if (rotationEnabled) {
                        ActivityInfo.SCREEN_ORIENTATION_SENSOR
                    } else {
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    }
                }
        }

        setContent {
            MyApp(prefs = prefs)
        }
    }
}

@Composable
fun MyApp(prefs: PetPrefs) {
    var darkMode by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = "pref") {
        prefs.getDarkModeEnabled()
            .collect { darkModeEnabled ->
                darkMode = darkModeEnabled
            }
    }
    LovelyPetsTheme (darkTheme = darkMode) {
        LovelyPetsNavHost(prefs = prefs)
    }
    
}