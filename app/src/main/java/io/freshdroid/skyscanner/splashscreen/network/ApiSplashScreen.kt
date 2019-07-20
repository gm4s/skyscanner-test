package io.freshdroid.skyscanner.splashscreen.network

import com.squareup.moshi.Moshi
import io.freshdroid.skyscanner.core.network.ApiCore
import io.freshdroid.skyscanner.core.network.HttpTransitionFactoryType
import javax.inject.Inject

class ApiSplashScreen @Inject constructor(
    private val _httpTransitionFactory: HttpTransitionFactoryType,
    private val _moshi: Moshi
) : ApiCore(_moshi), ApiSplashScreenType