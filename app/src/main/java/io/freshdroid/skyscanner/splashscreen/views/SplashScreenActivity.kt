package io.freshdroid.skyscanner.splashscreen.views

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.autoDisposable
import io.freshdroid.skyscanner.R
import io.freshdroid.skyscanner.core.extensions.launchIntent
import io.freshdroid.skyscanner.core.ui.TransitionUtils.fadeIn
import io.freshdroid.skyscanner.core.rx.transformers.Transformers.observeForUI
import io.freshdroid.skyscanner.core.ui.BaseActivity
import io.freshdroid.skyscanner.coreComponent
import io.freshdroid.skyscanner.navigation.UriResolver
import io.freshdroid.skyscanner.splashscreen.di.SplashScreenComponentManager
import io.freshdroid.skyscanner.splashscreen.viewmodels.SplashScreenViewModel

class SplashScreenActivity : BaseActivity() {

    private val _component by lazy {
        SplashScreenComponentManager.splashScreenComponent(coreComponent())
    }
    private val _viewModelFactory by lazy {
        SplashScreenViewModel.Factory(_component.environment(), scopeProvider)
    }
    private val _viewModel by lazy {
        ViewModelProviders.of(this, _viewModelFactory).get(SplashScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        _viewModel.outputs.launchNextActivity()
            .compose(observeForUI())
            .map(UriResolver::resolve)
            .autoDisposable(scopeProvider)
            .subscribe { this.launchIntent(it, fadeIn()) }

        _viewModel.intent(intent)
        _viewModel.inputs.fakeLoading()
    }

}