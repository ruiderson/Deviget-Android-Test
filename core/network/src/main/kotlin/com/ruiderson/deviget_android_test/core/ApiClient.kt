package com.ruiderson.deviget_android_test.core

import io.ktor.client.HttpClient

interface ApiClient {

    fun getHttpClient(): HttpClient
}
