package io.freshdroid.skyscanner.core.di

import android.content.Context
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.freshdroid.skyscanner.core.BuildConfig
import io.freshdroid.skyscanner.core.network.*
import io.freshdroid.skyscanner.core.network.interceptors.ApiRequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        context: Context,
        apiRequestInterceptor: ApiRequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        val client = OkHttpClient.Builder()
        client.addInterceptor(apiRequestInterceptor)
        client.pingInterval(20, TimeUnit.SECONDS)

        val protocols = arrayListOf(
            Protocol.HTTP_2,
            Protocol.HTTP_1_1
        )
        client.protocols(protocols)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
            client.addInterceptor(ChuckInterceptor(context))
            client.addInterceptor(CurlInterceptor(Loggable { Timber.d("D/OkHttp: $it") }))
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun provideApiRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return createRetrofit(ApiEndpoint.SKYSCANNER.url, okHttpClient)
    }

    private fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(StringConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpTransitionFactoryType(apiService: ApiService): HttpTransitionFactoryType {
        return HttpTransitionFactory(apiService)
    }

}