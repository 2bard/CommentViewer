package com.twobard.techtest


import com.twobard.techtest.data.JsonPlaceholderApiService

import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.test.Test
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE,
    sdk = [33])
class LiveApiTest {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.ENDPOINT_URL) // real API
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api = retrofit.create(JsonPlaceholderApiService::class.java)

    @Test
    fun `fetch items from real API`() = runTest {
        val items = api.getComments()
        assertTrue(items.isNotEmpty())
    }
}