package com.example.pokemon.data.networking

import android.util.Log
import com.example.pokemon.domain.PokemonInfoModel
import com.example.pokemon.domain.PokemonList
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NetworkController @Inject constructor() {

    fun getOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    Log.d("OK HTTP", message)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
            .connectTimeout(10000L, TimeUnit.SECONDS)
            .readTimeout(10000L, TimeUnit.SECONDS)
            .writeTimeout(10000L, TimeUnit.SECONDS)
            .build()



}
