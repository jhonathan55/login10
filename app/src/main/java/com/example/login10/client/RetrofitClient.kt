package com.example.login10.client

import com.example.login10.services.LoginService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //localhost solo para uso de telefono
    private const val BASE_URL = "http://10.0.2.2:3008/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
                val cookies = originalResponse.headers("Set-Cookie")
                print("Cookies: $cookies")
            }
            originalResponse
        }
        .build()
    fun getClient(): OkHttpClient {
        return client
    }
    val instance: LoginService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(LoginService::class.java)
    }

}