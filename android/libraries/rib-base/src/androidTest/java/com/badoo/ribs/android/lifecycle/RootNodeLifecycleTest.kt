package com.badoo.ribs.android.lifecycle

import android.arch.lifecycle.Lifecycle
import com.badoo.common.ribs.RibsRule
import com.badoo.ribs.test.util.ribs.root.TestRoot
import com.badoo.ribs.test.util.finishActivitySync
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class RootNodeLifecycleTest {
    private val provider = TestRoot.Provider()
    val node get() = provider.rootNode!!

    @get:Rule
    val ribsRule = RibsRule { activity, savedInstanceState -> provider.create(activity.dialogLauncher(), savedInstanceState) }

    @Test
    fun whenActivityResumed_nodeIsAttached() {
        assertThat(node.isAttached).isTrue()
    }

    @Test
    fun whenActivityResumed_viewIsAttached() {
        assertThat(node.isAttachedToView).isTrue()
    }

    @Test
    fun whenActivityResumed_lifecycleEventsAreDispatched() {
        provider.viewLifecycleObserver.assertValues(
            Lifecycle.Event.ON_CREATE,
            Lifecycle.Event.ON_START,
            Lifecycle.Event.ON_RESUME
        )
    }

    @Test
    fun whenActivityDestroyed_nodeIsDetached() {
        ribsRule.finishActivitySync()

        assertThat(node.isAttached).isFalse()
    }

    @Test
    fun whenActivityDestroyed_viewIsDetached() {
        ribsRule.finishActivitySync()

        assertThat(node.isAttachedToView).isFalse()
    }

    @Test
    fun whenActivityDestroyed_lifecycleEventsAreDispatched() {
        val viewLifecycleObserver = provider.viewLifecycleObserver
        viewLifecycleObserver.clear()

        ribsRule.finishActivitySync()

        viewLifecycleObserver.assertValues(
            Lifecycle.Event.ON_PAUSE,
            Lifecycle.Event.ON_STOP,
            Lifecycle.Event.ON_DESTROY
        )
    }
}
