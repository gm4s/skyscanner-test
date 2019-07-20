package io.freshdroid.skyscanner.splashscreen

import io.freshdroid.skyscanner.splashscreen.network.ApiSplashScreenType
import io.reactivex.Scheduler
import javax.inject.Inject

data class SplashScreenEnvironment @Inject constructor(
    val apiSplashScreen: ApiSplashScreenType,
    val scheduler: Scheduler
)