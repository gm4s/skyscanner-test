package io.freshdroid.skyscanner.search

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.android.lifecycle.test.TestLifecycleOwner
import io.freshdroid.skyscanner.core.di.CoreComponent
import junit.framework.TestCase
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = SkyscannerApplicationTest::class)
internal abstract class SkyscannerRobolectricTestCase : TestCase() {

    private var _application: SkyscannerApplicationTest? = null

    protected fun application(): SkyscannerApplicationTest {
        if (_application != null) {
            return _application as SkyscannerApplicationTest
        }

        _application = ApplicationProvider.getApplicationContext() as SkyscannerApplicationTest
        return _application as SkyscannerApplicationTest
    }

    protected fun context(): Context {
        return application().applicationContext
    }

    protected fun coreComponent(): CoreComponent {
        return application().coreComponent()
    }

    protected fun scopeProvider(): AndroidLifecycleScopeProvider {
        val testLifecycleOwner = TestLifecycleOwner.create()
        return AndroidLifecycleScopeProvider.from(testLifecycleOwner)
    }

}