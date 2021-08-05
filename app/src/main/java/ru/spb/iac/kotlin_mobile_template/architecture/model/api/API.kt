package ru.spb.iac.kotlin_mobile_template.architecture.model.api

import okhttp3.internal.http2.Header
import okhttp3.logging.HttpLoggingInterceptor
import ru.spb.iac.kotlin_mobile_template.activitities.model.api.AuthorizationApi

/**
 * Created by nixbyte on 20,Декабрь,2019
 */

object API {

    private const val TEST_API = "http://api.test.events4me.iac.spb.ru/"
    private const val PRODUCTION_API = "http://api.events4me.iac.spb.ru/"
    private const val API_VERSION = ""

    const val DEFAULT_HOST = TEST_API + API_VERSION

    private val A = ApiConstructor.Builder()
        .setHeader(Header("Content-Type","application/json")) //set header for single value headers like Content-Type, Authorization etc
//        .addHeader(Header("Authorization","Bearer asdfadf")) //add header method for multiple value headers list Cache-Control
        .setLogLevel(HttpLoggingInterceptor.Level.BODY)

    fun getAuthorizationApi(): AuthorizationApi {
        return A.create(AuthorizationApi::class.java)
    }

    fun getUserService(): AuthorizationApi {
        return A.create(AuthorizationApi::class.java)
    }
}
