package com.badoo.ribs.test.util.ribs.root

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Router
import com.badoo.ribs.test.util.ribs.root.TestRootRouter.Configuration
import io.reactivex.observers.TestObserver
import android.os.Bundle

class TestRootInteractor(
    savedInstanceState: Bundle?,
    router: Router<Configuration, Configuration.Permanent, Configuration.Content, Configuration.Overlay, TestRootView>,
    private val viewLifecycleObserver: TestObserver<Lifecycle.Event>
) : Interactor<Configuration, Configuration.Content, Configuration.Overlay, TestRootView>(
    savedInstanceState = savedInstanceState,
    router = router,
    disposables = null
) {

    override fun onViewCreated(view: TestRootView, viewLifecycle: Lifecycle) {
        viewLifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            fun onEach(owner: LifecycleOwner, event: Lifecycle.Event) {
                viewLifecycleObserver.onNext(event)
            }
        })
    }
}
