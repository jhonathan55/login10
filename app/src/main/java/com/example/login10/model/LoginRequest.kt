package com.example.login10.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val id: String,
    val message: String,
    val loginAttempts: Int?=null,
    val lockUntil: String?=null
)
