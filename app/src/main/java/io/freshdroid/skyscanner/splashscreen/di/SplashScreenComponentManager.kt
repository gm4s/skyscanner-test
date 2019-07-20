package io.freshdroid.skyscanner.splashscreen.di

import io.freshdroid.skyscanner.core.di.CoreComponent
import javax.inject.Singleton

@Singleton
object SplashScreenComponentManager {

    private var splashScreenComponent: SplashScreenComponent? = null

    fun splashScreenComponent(coreComponent: CoreComponent): SplashScreenComponent {
        if (splashScreenComponent == null)
            splashScreenComponent = DaggerSplashScreenComponent.builder().coreComponent(coreComponent).build()
        return splashScreenComponent as SplashScreenComponent
    }

    fun destroySplashScreenComponent() {
        splashScreenComponent = null
    }

}