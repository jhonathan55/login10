package com.example.login10.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login10.client.RetrofitClient
import com.example.login10.model.LoginRequest
import com.example.login10.model.LoginResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult = _loginResult

    fun login(user: LoginRequest) {
        val call = RetrofitClient.instance.login(user)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()!!
                    _loginResult.value = Result.success(
                        LoginResponse(
                            body.message, body.id
                        )
                    )
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(
                        errorBody, LoginResponse::class.java
                    )
                    println("error del back" + errorResponse)
                    _loginResult.value = Result.failure(Exception(errorResponse.message))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _loginResult.value = Result.failure(t)
            }
        })
    }
}