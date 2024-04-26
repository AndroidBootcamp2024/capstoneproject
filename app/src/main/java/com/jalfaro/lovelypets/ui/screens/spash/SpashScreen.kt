package com.jalfaro.lovelypets.ui.screens.spash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jalfaro.lovelypets.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToNextScreen : () -> Unit
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lovelypets_splash)
    )
    LaunchedEffect(true) {
        delay(5000)
        navigateToNextScreen()
    }

    Column(

    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top=40.dp),
        )
        LottieAnimation(
            composition = composition,
            modifier = Modifier.fillMaxSize(),
            iterations = LottieConstants.IterateForever,
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen (navigateToNextScreen = {})
}