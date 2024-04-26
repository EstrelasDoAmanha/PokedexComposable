package com.pokedexcompose.designsystem.components.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun Lottie(url: String) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(url))
    LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
}
