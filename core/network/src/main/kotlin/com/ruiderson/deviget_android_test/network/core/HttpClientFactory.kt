package com.ruiderson.deviget_android_test.network.core

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.features.DefaultRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.host
import io.ktor.http.URLProtocol

internal class HttpClientFactory {

    fun create(
        baseUrl: String,
        urlProtocol: URLProtocol,
        timeOut: Int = DEFAULT_TIMEOUT,
        onHeaderConfiguration: (HttpRequestBuilder) -> Unit
    ) = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                connectTimeout = timeOut
                socketTimeout = timeOut
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            host = baseUrl
            url {
                protocol = urlProtocol
            }
            onHeaderConfiguration(this)
        }
    }

    companion object {
        private const val DEFAULT_TIMEOUT = 60000
    }
}
