@file:JvmName("ActivityHelper")

package io.freshdroid.skyscanner.navigation

import android.content.Intent

private const val PACKAGE_NAME = "io.freshdroid.skyscanner"

fun intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(Intent.ACTION_VIEW).setClassName(
        PACKAGE_NAME,
        addressableActivity.className
    )
}

interface AddressableActivity {
    val className: String
}

object Activities {

    object Search : AddressableActivity {
        override val className = "$PACKAGE_NAME.search.views.SearchActivity"
    }

}