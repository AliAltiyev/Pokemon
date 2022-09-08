package com.example.pokemon.di

import android.util.Log
import com.example.pokemon.data.data.network.PokemonApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://pokeapi.co/api/v2/"
private const val OK_HTTP_CLIENT = "OK HTTP"

@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {

    @Provides
    @Singleton
    fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor { message ->
                Log.d(OK_HTTP_CLIENT, message)
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
        .connectTimeout(10000L, TimeUnit.SECONDS)
        .readTimeout(10000L, TimeUnit.SECONDS)
        .writeTimeout(10000L, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(getOkHttpClient())
        .build()


    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): PokemonApi =
        retrofit.create(PokemonApi::class.java)
}


