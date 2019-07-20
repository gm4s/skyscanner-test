package io.freshdroid.skyscanner.viewmodels

import android.net.Uri
import io.freshdroid.skyscanner.SkyscannerRobolectricTestCase
import io.freshdroid.skyscanner.navigation.ApplicationMap
import io.freshdroid.skyscanner.splashscreen.SplashScreenEnvironment
import io.freshdroid.skyscanner.splashscreen.di.DaggerSplashScreenComponent
import io.freshdroid.skyscanner.splashscreen.di.SplashScreenComponent
import io.freshdroid.skyscanner.splashscreen.viewmodels.SplashScreenViewModel
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

internal class SplashScreenViewModelTest : SkyscannerRobolectricTestCase() {

    private lateinit var component: SplashScreenComponent

    @Before
    fun initComponent() {
        component = DaggerSplashScreenComponent.builder().coreComponent(coreComponent()).build()
    }

    private fun environment(): SplashScreenEnvironment = component.environment()

    @Test
    fun testLaunchNextActivity() {
        val testScheduler = TestScheduler()
        val environment = environment().copy(
            scheduler = testScheduler
        )
        val vm = SplashScreenViewModel(environment, scopeProvider())
        val launchNextActivity = TestSubscriber<Uri>()
        vm.outputs.launchNextActivity().subscribe(launchNextActivity::onNext)

        vm.inputs.fakeLoading()
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        launchNextActivity.assertValue { it == Uri.parse(ApplicationMap.SEARCH) }
    }

}