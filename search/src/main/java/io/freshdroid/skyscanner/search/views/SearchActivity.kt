package io.freshdroid.skyscanner.search.views

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.uber.autodispose.autoDisposable
import io.freshdroid.skyscanner.core.extensions.showToastApiError
import io.freshdroid.skyscanner.core.rx.transformers.Transformers.observeForUI
import io.freshdroid.skyscanner.core.ui.BaseActivity
import io.freshdroid.skyscanner.coreComponent
import io.freshdroid.skyscanner.search.R
import io.freshdroid.skyscanner.search.di.SearchComponentManager
import io.freshdroid.skyscanner.search.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private val _component by lazy {
        SearchComponentManager.searchComponent(coreComponent())
    }
    private val _viewModelFactory by lazy {
        SearchViewModel.Factory(_component.environment(), scopeProvider)
    }
    private val _viewModel by lazy {
        ViewModelProviders.of(this, _viewModelFactory).get(SearchViewModel::class.java)
    }

    private lateinit var _itinerariesAdapter: ItinerariesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initViews()

        _viewModel.outputs.itineraries()
            .compose(observeForUI())
            .autoDisposable(scopeProvider)
            .subscribe(_itinerariesAdapter::takeItems)

        _viewModel.outputs.displayLoader()
            .compose(observeForUI())
            .autoDisposable(scopeProvider)
            .subscribe { }

        _viewModel.errors.fetchItinerariesApiError()
            .compose(observeForUI())
            .autoDisposable(scopeProvider)
            .subscribe { this.showToastApiError(it) }

        _viewModel.intent(intent)
        _viewModel.inputs.fetchRoundTripTicketBUDToLDN()
    }

    private fun initViews() {
        val layoutManager = LinearLayoutManager(itinerariesRecyclerView.context)
        itinerariesRecyclerView.layoutManager = layoutManager
        itinerariesRecyclerView.itemAnimator = DefaultItemAnimator()

        _itinerariesAdapter = ItinerariesAdapter(_viewModel.inputs)
        itinerariesRecyclerView.adapter = _itinerariesAdapter
    }

}