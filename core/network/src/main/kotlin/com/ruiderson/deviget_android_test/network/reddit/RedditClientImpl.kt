package com.ruiderson.deviget_android_test.network.reddit

import com.ruiderson.deviget_android_test.network.core.HttpClientFactory
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol

internal class RedditClientImpl(
    httpClientFactory: HttpClientFactory
) : RedditClient() {

    override val client = httpClientFactory.create(
        baseUrl = BASE_URL,
        urlProtocol = URLProtocol.HTTPS,
        onHeaderConfiguration = {
            it.header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    )

    companion object {
        private const val BASE_URL = "reddit.com"
    }
}
