package io.freshdroid.skyscanner.splashscreen.viewmodels.outputs

import android.net.Uri
import io.reactivex.Observable

interface SplashScreenViewModelOutputs {

    fun launchNextActivity(): Observable<Uri>

}