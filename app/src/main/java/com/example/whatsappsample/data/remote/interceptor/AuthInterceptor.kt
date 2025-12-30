package com.example.whatsappsample.data.remote.interceptor

import com.example.whatsappsample.data.local.AppPreferences
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.encodedPath
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val appPreferences: AppPreferences
) {
    fun attach(client: HttpClient) {
        client.plugin(HttpSend).intercept { request ->
            val path = request.url.encodedPath
            val isExcluded = path.contains("auth/login") ||
                    path.contains("auth/register") ||
                    path.contains("auth/reset-password")

            if (!isExcluded) {
                appPreferences.getToken()?.let { token ->
                    request.header(HttpHeaders.Authorization, "Bearer $token")
                }
            }
            execute(request)
        }
    }
}
