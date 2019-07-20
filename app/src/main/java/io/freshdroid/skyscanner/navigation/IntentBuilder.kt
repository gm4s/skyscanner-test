package io.freshdroid.skyscanner.navigation

import android.content.Intent
import android.net.Uri

object IntentBuilder {

    @JvmStatic
    fun build(uri: Uri): Intent {
        return UriResolver.resolve(uri)
    }

}