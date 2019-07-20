package io.freshdroid.skyscanner.splashscreen.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.freshdroid.skyscanner.splashscreen.SplashScreenEnvironment
import io.freshdroid.skyscanner.splashscreen.network.ApiSplashScreen
import io.freshdroid.skyscanner.splashscreen.network.ApiSplashScreenType
import io.freshdroid.skyscanner.core.network.HttpTransitionFactoryType
import io.reactivex.Scheduler

@Module
class SplashScreenModule {

    @Provides
    @SplashScreenScope
    fun provideSplashScreenEnvironment(
        apiSplashScreen: ApiSplashScreenType,
        scheduler: Scheduler
    ): SplashScreenEnvironment {
        return SplashScreenEnvironment(
            apiSplashScreen,
            scheduler
        )
    }

    @Provides
    @SplashScreenScope
    fun provideApiSplashScreenType(
        httpTransitionFactory: HttpTransitionFactoryType,
        moshi: Moshi
    ): ApiSplashScreenType {
        return ApiSplashScreen(
            httpTransitionFactory,
            moshi
        )
    }

}