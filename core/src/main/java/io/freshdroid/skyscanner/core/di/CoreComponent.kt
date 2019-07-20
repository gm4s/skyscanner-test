package io.freshdroid.skyscanner.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Component
import io.freshdroid.skyscanner.core.network.HttpTransitionFactoryType
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun application(): Application
    fun context(): Context
    fun sharedPreferences(): SharedPreferences
    fun httpTransitionFactory(): HttpTransitionFactoryType
    fun okHttpClient(): OkHttpClient
    fun moshi(): Moshi
    fun scheduler(): Scheduler

}