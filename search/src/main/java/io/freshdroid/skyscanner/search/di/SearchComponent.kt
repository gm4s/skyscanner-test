package io.freshdroid.skyscanner.search.di

import dagger.Component
import io.freshdroid.skyscanner.core.di.CoreComponent
import io.freshdroid.skyscanner.search.SearchEnvironment

@SearchScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [SearchModule::class]
)
interface SearchComponent {

    fun environment(): SearchEnvironment

}