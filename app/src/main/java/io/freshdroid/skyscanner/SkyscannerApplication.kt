package io.freshdroid.skyscanner

import android.content.Context
import androidx.multidex.MultiDexApplication
import io.freshdroid.skyscanner.core.di.CoreComponent
import io.freshdroid.skyscanner.core.di.CoreModule
import io.freshdroid.skyscanner.core.di.DaggerCoreComponent
import io.freshdroid.skyscanner.core.ui.BaseActivity
import timber.log.Timber

open class SkyscannerApplication : MultiDexApplication() {

    private val _coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .coreModule(CoreModule(this))
            .build()
    }

    var isAppOnForeground = false

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) = (context.applicationContext as SkyscannerApplication)._coreComponent
    }

    fun coreComponent(): CoreComponent = _coreComponent

    protected open fun isInUnitTests(): Boolean {
        return false
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}

fun BaseActivity.coreComponent() = SkyscannerApplication.coreComponent(this)
