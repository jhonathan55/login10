package com.example.login10.services

import com.example.login10.model.LoginRequest
import com.example.login10.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}