package io.freshdroid.skyscanner.search

import io.freshdroid.skyscanner.SkyscannerApplication

internal class SkyscannerApplicationTest : SkyscannerApplication() {

    override fun isInUnitTests(): Boolean = true

    override fun onCreate() {
        super.onCreate()
    }

}