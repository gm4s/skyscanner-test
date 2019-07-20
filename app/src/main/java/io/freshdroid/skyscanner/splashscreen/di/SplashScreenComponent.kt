package io.freshdroid.skyscanner.splashscreen.di

import dagger.Component
import io.freshdroid.skyscanner.splashscreen.SplashScreenEnvironment
import io.freshdroid.skyscanner.core.di.CoreComponent

@SplashScreenScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [SplashScreenModule::class]
)
interface SplashScreenComponent {

    fun environment(): SplashScreenEnvironment

}