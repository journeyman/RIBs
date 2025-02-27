package com.badoo.ribs.core.helper

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Rib
import com.nhaarman.mockitokotlin2.mock

open class TestNode(
    savedInstanceState: Bundle?,
    identifier: Rib,
    viewFactory: ((ViewGroup) -> TestView?)? = mock()
) : Node<TestView>(
    savedInstanceState = savedInstanceState,
    identifier = identifier,
    viewFactory = viewFactory,
    router = mock(),
    interactor = mock()
) {
    var handleBackPress: Boolean =
        false

    var handleBackPressInvoked: Boolean =
        false

    override fun handleBackPress(): Boolean =
        handleBackPress.also {
            handleBackPressInvoked = true
        }
}
