package com.ruiderson.deviget_android_test.reddit

import com.ruiderson.deviget_android_test.core.HttpClientFactory
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol

internal class RedditClientImpl(
    private val httpClientFactory: HttpClientFactory
) : RedditClient {

    override fun getHttpClient() = httpClientFactory.create(
        baseUrl = BASE_URL,
        urlProtocol = URLProtocol.HTTPS,
        onHeaderConfiguration = {
            it.header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    )

    companion object {
        private const val BASE_URL = "reddit.com/r"
    }
}
