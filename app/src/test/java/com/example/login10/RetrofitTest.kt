package com.example.login10

import com.example.login10.client.RetrofitClient
import com.example.login10.model.LoginRequest
import com.example.login10.model.LoginResponse
import com.example.login10.services.LoginService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: LoginService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(RetrofitClient.getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `login success`() {
        val mockResponse= MockResponse()
            .setResponseCode(200)
            .setBody("{\"message\":\"ok\",\"id\":\"668dd359ae90f5d667f0ac23\"}")
        mockWebServer.enqueue(mockResponse)
        val response:Response<LoginResponse> = service.login(LoginRequest
            ("username", "password")).execute()
        assertTrue(response.isSuccessful)
        assertEquals("ok", response.body()?.message)
        assertEquals("668dd359ae90f5d667f0ac23", response.body()?.id)
    }

    @Test
    fun `login failure invalid email`()= runBlocking{
        val mockResponse = MockResponse()
            .setResponseCode(452)
            .setBody("{\"message\":\"Invalid email\"}")
        mockWebServer.enqueue(mockResponse)

        val response: Response<LoginResponse> = service.login(LoginRequest
            ("username", "password")).execute()

        assertFalse(response.isSuccessful)
        assertEquals("{\"message\":\"Invalid email\"}", response.errorBody()?.string())
        assertEquals(452, response.code())
    }

    //testear password incorrecto

}