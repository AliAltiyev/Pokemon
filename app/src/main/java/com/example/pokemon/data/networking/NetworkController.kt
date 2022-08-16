package com.example.pokemon.data.networking

import android.util.Log
import com.example.pokemon.domain.PokemonInfoModel
import com.example.pokemon.domain.PokemonList
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkController {

    private fun getOkHttpClient() =
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


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonApi::class.java)


    suspend fun getData(): Response<PokemonList> = retrofit.getPokemonList(
        POKEMON_LIMIT_SIZE,
        POKEMON_OFFSET_SIZE
    )

    suspend fun getPokemon(id : Int): Response<PokemonInfoModel> = retrofit.getPokemonInfo(id)


    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        private const val POKEMON_LIMIT_SIZE = 100
        private const val POKEMON_OFFSET_SIZE = 0

    }

}
