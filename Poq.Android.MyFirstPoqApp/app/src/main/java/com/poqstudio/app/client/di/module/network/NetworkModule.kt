package com.poqstudio.app.client.di.module.network

import com.poqstudio.app.platform.di.getPoq
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkModule = module {
    single(override = true) { getPoq<List<Interceptor>>() + get<ChuckInterceptor>() }
    single { ChuckInterceptor(androidApplication()) }
}
