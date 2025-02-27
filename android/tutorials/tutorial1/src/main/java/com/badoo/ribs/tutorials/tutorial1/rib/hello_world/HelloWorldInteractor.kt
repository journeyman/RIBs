package com.badoo.ribs.tutorials.tutorial1.rib.hello_world

import android.arch.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.mvicore.binder.using
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.Router
import com.badoo.ribs.tutorials.tutorial1.rib.hello_world.HelloWorldRouter.Configuration
import com.badoo.ribs.tutorials.tutorial1.rib.hello_world.HelloWorldRouter.Configuration.Content
import com.badoo.ribs.tutorials.tutorial1.rib.hello_world.mapper.ViewEventToOutput
import io.reactivex.functions.Consumer
import android.os.Bundle

class HelloWorldInteractor(
    savedInstanceState: Bundle?,
    router: Router<Configuration, Nothing, Content, Nothing, HelloWorldView>,
    private val output: Consumer<HelloWorld.Output>
) : Interactor<Configuration, Content, Nothing, HelloWorldView>(
    savedInstanceState = savedInstanceState,
    router = router,
    disposables = null
) {

    override fun onViewCreated(view: HelloWorldView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(view to output using ViewEventToOutput)
        }
    }
}
