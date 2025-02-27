package com.badoo.ribs.example.rib.lorem_ipsum

import android.os.Bundle
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.badoo.common.ribs.RibsRule
import com.badoo.ribs.RibTestActivity
import com.badoo.ribs.customisation.RibCustomisationDirectory
import com.badoo.ribs.example.R
import com.badoo.ribs.example.app.AppRibCustomisations
import com.badoo.ribs.example.rib.lorem_ipsum.builder.LoremIpsumBuilder
import io.reactivex.functions.Consumer
import org.junit.Rule
import org.junit.Test

class LoremIpsumTest {

    @get:Rule
    val ribsRule = RibsRule { activity, savedInstanceState -> buildRib(activity, savedInstanceState) }

    private fun buildRib(ribTestActivity: RibTestActivity, savedInstanceState: Bundle?) =
        LoremIpsumBuilder(object : LoremIpsum.Dependency {
            override fun ribCustomisation(): RibCustomisationDirectory = AppRibCustomisations
            override fun loremIpsumOutput(): Consumer<LoremIpsum.Output> = Consumer {}
        }).build(savedInstanceState)

    @Test
    fun testTextDisplayed() {
         onView(withId(R.id.lorem_ipsum_text)).check(matches(isDisplayed()))
    }
}
