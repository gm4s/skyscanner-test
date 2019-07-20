package io.freshdroid.skyscanner.search.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.freshdroid.skyscanner.core.network.ErrorEnvelope
import io.freshdroid.skyscanner.core.rx.Irrelevant
import io.freshdroid.skyscanner.core.rx.transformers.Transformers.pipeApiErrorsTo
import io.freshdroid.skyscanner.core.viewmodel.ActivityViewModel
import io.freshdroid.skyscanner.search.SearchEnvironment
import io.freshdroid.skyscanner.search.di.SearchComponentManager
import io.freshdroid.skyscanner.models.Itinerary
import io.freshdroid.skyscanner.search.viewmodels.errors.SearchViewModelErrors
import io.freshdroid.skyscanner.search.viewmodels.intputs.SearchViewModelInputs
import io.freshdroid.skyscanner.search.viewmodels.outputs.SearchViewModelOutputs
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class SearchViewModel(
    environment: SearchEnvironment,
    scopeProvider: AndroidLifecycleScopeProvider
) : ActivityViewModel(), SearchViewModelInputs, SearchViewModelOutputs, SearchViewModelErrors {

    private val _fetchRoundTripTicketBUDToLDN = PublishSubject.create<Irrelevant>()
    private val _itineraries = PublishSubject.create<ArrayList<Itinerary>>()
    private val _fetchItinerariesApiError = PublishSubject.create<ErrorEnvelope>()
    private val _displayLoader = PublishSubject.create<Boolean>()

    private val _apiSearch = environment.apiSearch

    val inputs: SearchViewModelInputs = this
    val outputs: SearchViewModelOutputs = this
    val errors: SearchViewModelErrors = this

    init {
        _fetchRoundTripTicketBUDToLDN
            .doOnNext { _displayLoader.onNext(true) }
            .switchMap { this.fetchItineraries() }
            .doOnNext { _displayLoader.onNext(false) }
            .autoDisposable(scopeProvider)
            .subscribe(_itineraries::onNext)

        _fetchItinerariesApiError
            .autoDisposable(scopeProvider)
            .subscribe { _displayLoader.onNext(false) }
    }

    override fun onCleared() {
        super.onCleared()
        SearchComponentManager.destroySearchComponent()
    }

    // INPUTS

    override fun fetchRoundTripTicketBUDToLDN() {
        _fetchRoundTripTicketBUDToLDN.onNext(Irrelevant.INSTANCE)
    }

    // OUTPUTS

    override fun itineraries(): Observable<ArrayList<Itinerary>> = _itineraries

    override fun displayLoader(): Observable<Boolean> = _displayLoader

    // ERRORS

    override fun fetchItinerariesApiError(): Observable<ErrorEnvelope> = _fetchItinerariesApiError

    private fun fetchItineraries(): Observable<ArrayList<Itinerary>> {
        return _apiSearch.fetchItineraries()
            .compose(pipeApiErrorsTo(_fetchItinerariesApiError))
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val _environment: SearchEnvironment,
        private val _scopeProvider: AndroidLifecycleScopeProvider
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchViewModel(_environment, _scopeProvider) as T
        }
    }

}