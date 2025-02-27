package com.badoo.ribs.example.rib.hello_world

import android.os.Parcelable
import com.badoo.ribs.core.Router
import com.badoo.ribs.core.routing.action.RoutingAction
import com.badoo.ribs.core.routing.action.RoutingAction.Companion.noop
import com.badoo.ribs.example.rib.hello_world.HelloWorldRouter.Configuration
import kotlinx.android.parcel.Parcelize
import android.os.Bundle

class HelloWorldRouter(
    savedInstanceState: Bundle?
): Router<Configuration, Nothing, Configuration, Nothing, HelloWorldView>(
    savedInstanceState = savedInstanceState,
    initialConfiguration = Configuration.Default
) {
    sealed class Configuration : Parcelable {
        @Parcelize object Default : Configuration()
    }

    override fun resolveConfiguration(configuration: Configuration): RoutingAction<HelloWorldView> =
        when (configuration) {
            Configuration.Default -> noop()
    }
}
