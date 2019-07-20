package io.freshdroid.skyscanner.search.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.freshdroid.skyscanner.core.network.HttpTransitionFactoryType
import io.freshdroid.skyscanner.search.SearchEnvironment
import io.freshdroid.skyscanner.search.network.ApiSearch
import io.freshdroid.skyscanner.search.network.ApiSearchType
import io.reactivex.Scheduler

@Module
class SearchModule {

    @Provides
    @SearchScope
    fun provideSplashScreenEnvironment(
        apiSearchType: ApiSearchType,
        scheduler: Scheduler
    ): SearchEnvironment {
        return SearchEnvironment(
            apiSearchType,
            scheduler
        )
    }

    @Provides
    @SearchScope
    fun provideApiSearchType(
        httpTransitionFactory: HttpTransitionFactoryType,
        moshi: Moshi
    ): ApiSearchType {
        return ApiSearch(
            httpTransitionFactory,
            moshi
        )
    }

}