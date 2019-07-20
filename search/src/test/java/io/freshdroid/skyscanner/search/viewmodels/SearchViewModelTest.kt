package io.freshdroid.skyscanner.search.viewmodels

import io.freshdroid.skyscanner.core.network.ErrorEnvelope
import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.search.SearchEnvironment
import io.freshdroid.skyscanner.search.SkyscannerRobolectricTestCase
import io.freshdroid.skyscanner.search.di.DaggerSearchComponent
import io.freshdroid.skyscanner.search.di.SearchComponent
import io.freshdroid.skyscanner.search.exceptions.ApiExceptionFactory
import io.freshdroid.skyscanner.search.factories.ItineraryFactory
import io.freshdroid.skyscanner.search.mocks.MockApiSearch
import io.reactivex.Observable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

internal class SearchViewModelTest : SkyscannerRobolectricTestCase() {

    private lateinit var component: SearchComponent

    @Before
    fun initComponent() {
        component = DaggerSearchComponent.builder().coreComponent(coreComponent()).build()
    }

    private fun environment(): SearchEnvironment = component.environment()

    @Test
    fun testItineraries() {
        val environment = environment().copy(
            apiSearch = object : MockApiSearch() {
                override fun fetchItineraries(): Observable<ArrayList<Itinerary>> {
                    return Observable.just(ItineraryFactory.creator())
                }
            }
        )
        val vm = SearchViewModel(environment, scopeProvider())
        val itineraries = TestSubscriber<ArrayList<Itinerary>>()
        vm.outputs.itineraries().subscribe(itineraries::onNext)

        vm.inputs.fetchRoundTripTicketBUDToLDN()
        itineraries.assertValue { it.size == 1 }
    }

    @Test
    fun testItineraries_apiError() {
        val environment = environment().copy(
            apiSearch = object : MockApiSearch() {
                override fun fetchItineraries(): Observable<ArrayList<Itinerary>> {
                    return Observable.error(ApiExceptionFactory.badRequestException())
                }
            }
        )
        val vm = SearchViewModel(environment, scopeProvider())
        val fetchItinerariesApiError = TestSubscriber<ErrorEnvelope>()
        vm.errors.fetchItinerariesApiError().subscribe(fetchItinerariesApiError::onNext)

        vm.inputs.fetchRoundTripTicketBUDToLDN()
        fetchItinerariesApiError.assertValue { it.responseCode == 400 && it.message == "bad request" }
    }

    @Test
    fun testDisplayLoader() {
        val environment = environment().copy(
            apiSearch = object : MockApiSearch() {
                override fun fetchItineraries(): Observable<ArrayList<Itinerary>> {
                    return Observable.just(ItineraryFactory.creator())
                }
            }
        )
        val vm = SearchViewModel(environment, scopeProvider())
        val displayLoader = TestSubscriber<Boolean>()
        vm.outputs.displayLoader().subscribe(displayLoader::onNext)

        vm.inputs.fetchRoundTripTicketBUDToLDN()
        displayLoader.assertValues(true, false)
    }

}