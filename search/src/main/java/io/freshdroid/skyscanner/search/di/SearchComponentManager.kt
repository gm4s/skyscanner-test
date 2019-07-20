package io.freshdroid.skyscanner.search.di

import io.freshdroid.skyscanner.core.di.CoreComponent
import javax.inject.Singleton

@Singleton
object SearchComponentManager {

    private var searchComponent: SearchComponent? = null

    fun searchComponent(coreComponent: CoreComponent): SearchComponent {
        if (searchComponent == null)
            searchComponent = DaggerSearchComponent.builder().coreComponent(coreComponent).build()
        return searchComponent as SearchComponent
    }

    fun destroySearchComponent() {
        searchComponent = null
    }

}