package com.jalfaro.lovelypets.ui.screens

sealed interface Screen {
    val path: String

    data object Splash : Screen {
        override val path = "splash"
    }

    data object Menu : Screen {
        override val path = "menu"
    }

    data object Input : Screen {
        override val path = "input"
    }

    data object Display : Screen {
        override val path = "screen"
    }

    data object Settings : Screen {
        override val path = "settings"
    }

    data object About : Screen {
        override val path = "about"
    }
}