package com.twobard.techtest.ui

import CommentListPreviewProvider
import CommentPreviewProvider
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.twobard.techtest.ui.detail.DetailScreenPreview
import com.twobard.techtest.ui.list.CommentListScreen
import com.twobard.techtest.ui.list.ListScreenIsLoadingPreview
import com.twobard.techtest.ui.theme.TechTestTheme
import org.junit.Rule
import kotlin.test.Test


class DetailScreenPreviewTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun detailScreenPreview() {
        val provider = CommentPreviewProvider()
        provider.values.forEach {
            paparazzi.snapshot(name = "DetailScreen_{${it.hashCode()}") {
                TechTestTheme {
                    DetailScreenPreview(it)
                }
            }
        }
    }

    @Test
    fun listScreenPreview() {
        val provider = CommentListPreviewProvider()
        provider.values.forEach {
            paparazzi.snapshot(name = "ListScreen_{${it.hashCode()}") {
                TechTestTheme {
                    CommentListScreen(it)
                }
            }
        }
    }

    @Test
    fun listScreenLoadingPreview() {
        val provider = CommentListPreviewProvider()
        provider.values.forEach {
            paparazzi.snapshot(name = "LoadingListScreen_{${it.hashCode()}") {
                TechTestTheme {
                    ListScreenIsLoadingPreview()
                }
            }
        }
    }

}
