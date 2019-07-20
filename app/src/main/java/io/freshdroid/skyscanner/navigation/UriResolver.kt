package io.freshdroid.skyscanner.navigation

import android.content.Intent
import android.net.Uri

object UriResolver {

    @JvmStatic
    @Throws(IntentNotFoundException::class)
    fun resolve(uri: Uri): Intent = getIntent(uri)

    @Throws(IntentNotFoundException::class)
    private fun getIntent(uri: Uri): Intent {
        return when (uri.toString()) {
            ApplicationMap.SEARCH -> intentTo(Activities.Search)
            else -> throw IntentNotFoundException(uri)
        }
    }

}