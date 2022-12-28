package ru.sb066coder.bincheck.api

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sb066coder.bincheck.dto.CardInfo
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://lookup.binlist.net/"

private val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface BinApiService {

    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: Int): Response<CardInfo>

}

object BinApi {
    val service: BinApiService by lazy {
        retrofit.create()
    }
}