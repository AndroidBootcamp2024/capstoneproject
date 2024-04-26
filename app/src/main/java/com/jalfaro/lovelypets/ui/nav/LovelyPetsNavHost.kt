package com.jalfaro.lovelypets.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jalfaro.lovelypets.prefs.PetPrefs
import com.jalfaro.lovelypets.ui.screens.Screen
import com.jalfaro.lovelypets.ui.screens.about.AboutScreen
import com.jalfaro.lovelypets.ui.screens.display.DisplayScreen
import com.jalfaro.lovelypets.ui.screens.input.InputScreen
import com.jalfaro.lovelypets.ui.screens.menu.MenuScreen
import com.jalfaro.lovelypets.ui.screens.setting.SettingScreen
import com.jalfaro.lovelypets.ui.screens.spash.SplashScreen

@Composable
fun LovelyPetsNavHost(
    prefs: PetPrefs
) {
    val navController = rememberNavController()
    var allowDelete by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = "prefs") {
        prefs.getAllowDeleteEnabled()
            .collect {
                allowDelete = it
            }
    }

    NavHost(navController = navController, startDestination = Screen.Splash.path) {
        composable(Screen.Splash.path) {
            SplashScreen{
                navController.navigate(Screen.Menu.path)
            }
        }

        composable(Screen.Menu.path) {
            MenuScreen(
                onClickRegisterAPet = { navController.navigate(Screen.Input.path) },
                onClickViewAllPets = { navController.navigate(Screen.Display.path) },
                onClickSettings = { navController.navigate(Screen.Settings.path)},
                onClickAbout = { navController.navigate(Screen.About.path)}
            )
        }

        composable(Screen.Display.path) {
            DisplayScreen(
                onNavigateUp = { navController.navigateUp() },
                viewModel = hiltViewModel(),
                showDelete = allowDelete
            )
        }

        composable(Screen.Input.path) {
            InputScreen(
                onNavigateUp = { navController.navigateUp() },
                viewModel = hiltViewModel()
            )
        }

        composable(Screen.Settings.path) {
            SettingScreen(
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp()}
            )
        }

        composable(Screen.About.path) {
            AboutScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }

}