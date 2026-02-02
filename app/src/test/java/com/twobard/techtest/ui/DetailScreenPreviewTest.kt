package com.twobard.techtest.ui

import CommentPreviewProvider
import app.cash.paparazzi.Paparazzi
import com.twobard.techtest.ui.detail.DetailScreenPreview
import com.twobard.techtest.ui.theme.TechTestTheme
import org.junit.Rule
import kotlin.test.Test

class ComposePreviewTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun myComposable() {

        val provider = CommentPreviewProvider()
        provider.values.forEach {
            paparazzi.snapshot(name = "UserCard_{${it.id}") {
                TechTestTheme {
                    DetailScreenPreview(it)
                }
            }
        }
    }
}