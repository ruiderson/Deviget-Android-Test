package com.ruiderson.deviget_android_test.network.core

import io.ktor.client.HttpClient
import io.ktor.client.features.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.patch
import io.ktor.client.request.delete
import io.ktor.client.utils.EmptyContent

abstract class ApiClient {

    abstract val client: HttpClient

    suspend inline fun <reified T> get(
        path: String,
        body: Any = EmptyContent
    ): T = try {
        client.get(
            path = path,
            body = body
        )
    } catch (e: ResponseException) {
        throw HttpException(e.message, e.response.status.value)
    }

    suspend inline fun <reified T> post(
        path: String,
        body: Any = EmptyContent
    ): T = try {
        client.post(
            path = path,
            body = body
        )
    } catch (e: ResponseException) {
        throw HttpException(e.message, e.response.status.value)
    }

    suspend inline fun <reified T> put(
        path: String,
        body: Any = EmptyContent
    ): T = try {
        client.put(
            path = path,
            body = body
        )
    } catch (e: ResponseException) {
        throw HttpException(e.message, e.response.status.value)
    }

    suspend inline fun <reified T> delete(
        path: String,
        body: Any = EmptyContent
    ): T = try {
        client.delete(
            path = path,
            body = body
        )
    } catch (e: ResponseException) {
        throw HttpException(e.message, e.response.status.value)
    }

    suspend inline fun <reified T> patch(
        path: String,
        body: Any = EmptyContent
    ): T = try {
        client.patch(
            path = path,
            body = body
        )
    } catch (e: ResponseException) {
        throw HttpException(e.message, e.response.status.value)
    }
}
