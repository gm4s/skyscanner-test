package io.freshdroid.skyscanner.splashscreen.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.freshdroid.skyscanner.core.rx.Irrelevant
import io.freshdroid.skyscanner.core.viewmodel.ActivityViewModel
import io.freshdroid.skyscanner.navigation.ApplicationMap
import io.freshdroid.skyscanner.splashscreen.SplashScreenEnvironment
import io.freshdroid.skyscanner.splashscreen.di.SplashScreenComponentManager
import io.freshdroid.skyscanner.splashscreen.viewmodels.errors.SplashScreenViewModelErrors
import io.freshdroid.skyscanner.splashscreen.viewmodels.inputs.SplashScreenViewModelInputs
import io.freshdroid.skyscanner.splashscreen.viewmodels.outputs.SplashScreenViewModelOutputs
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SplashScreenViewModel(
    environment: SplashScreenEnvironment,
    scopeProvider: AndroidLifecycleScopeProvider
) : ActivityViewModel(), SplashScreenViewModelInputs, SplashScreenViewModelOutputs, SplashScreenViewModelErrors {

    private val _fakeLoading = PublishSubject.create<Irrelevant>()
    private val _launchNextActivity = PublishSubject.create<Uri>()

    private val _scheduler = environment.scheduler

    val inputs: SplashScreenViewModelInputs = this
    val outputs: SplashScreenViewModelOutputs = this
    val errors: SplashScreenViewModelErrors = this

    init {
        _fakeLoading
            .delay(1000, TimeUnit.MILLISECONDS, _scheduler)
            .autoDisposable(scopeProvider)
            .subscribe { _launchNextActivity.onNext(Uri.parse(ApplicationMap.SEARCH)) }
    }

    override fun onCleared() {
        super.onCleared()
        SplashScreenComponentManager.destroySplashScreenComponent()
    }

    // INPUTS

    override fun fakeLoading() {
        _fakeLoading.onNext(Irrelevant.INSTANCE)
    }

    // OUTPUTS

    override fun launchNextActivity(): Observable<Uri> = _launchNextActivity

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val _environment: SplashScreenEnvironment,
        private val _scopeProvider: AndroidLifecycleScopeProvider
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashScreenViewModel(_environment, _scopeProvider) as T
        }
    }

}