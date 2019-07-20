package io.freshdroid.skyscanner

internal class SkyscannerApplicationTest : SkyscannerApplication() {

    override fun isInUnitTests(): Boolean = true

    override fun onCreate() {
        super.onCreate()
    }

}