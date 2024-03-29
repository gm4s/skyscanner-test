@file:JvmName("BaseActivityExtension")

package io.freshdroid.skyscanner.core.extensions

import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ActivityOptionsCompat
import io.freshdroid.skyscanner.core.ui.TransitionUtils.fadeIn
import io.freshdroid.skyscanner.core.ui.TransitionUtils.slideInRight
import io.freshdroid.skyscanner.core.ui.TransitionUtils.transition
import io.freshdroid.skyscanner.core.network.ErrorEnvelope
import io.freshdroid.skyscanner.core.ui.BaseActivity

fun BaseActivity.showToastApiError(errorEnvelope: ErrorEnvelope, length: Int = Toast.LENGTH_LONG) {
    errorEnvelope.message?.let { Toast.makeText(this, it, length).show() }
}

fun BaseActivity.showToastError(@StringRes messageResource: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, getString(messageResource), length).show()
}

fun BaseActivity.showToastMessage(@StringRes messageResource: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, getString(messageResource), length).show()
}

fun BaseActivity.showToastMessage(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

fun BaseActivity.launchIntent(intent: Intent, transition: Pair<Int, Int> = slideInRight()) {
    startActivity(intent)
    transition(this, transition)
}

fun BaseActivity.launchIntents(intents: Array<Intent>) {
    startActivities(intents)
    transition(this, fadeIn())
}

fun BaseActivity.launchIntent(intent: Intent, options: ActivityOptionsCompat) {
    startActivity(intent, options.toBundle())
}

fun BaseActivity.launchIntentForResult(intent: Intent, requestCode: Int, transition: Pair<Int, Int> = slideInRight()) {
    startActivityForResult(intent, requestCode)
    transition(this, transition)
}