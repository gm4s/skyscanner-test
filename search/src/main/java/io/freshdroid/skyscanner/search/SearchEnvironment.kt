package io.freshdroid.skyscanner.search

import io.freshdroid.skyscanner.search.network.ApiSearchType
import io.reactivex.Scheduler
import javax.inject.Inject

data class SearchEnvironment @Inject constructor(
    val apiSearch: ApiSearchType,
    val scheduler: Scheduler
)