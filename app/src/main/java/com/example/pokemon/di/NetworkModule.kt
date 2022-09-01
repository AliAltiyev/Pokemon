package com.example.pokemon.di

import com.example.pokemon.data.networking.MainRemoteData
import com.example.pokemon.data.networking.NetworkController
import com.example.pokemon.data.networking.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofit(networkController: NetworkController): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(networkController.getOkHttpClient())
        .build()

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): PokemonApi =
        retrofit.create(PokemonApi::class.java)

    @Provides
    @Singleton
    fun provideMainRemoteData(pokemonApi: PokemonApi): MainRemoteData =
        MainRemoteData(pokemonApi)

    const val BASE_URL = "https://pokeapi.co/api/v2/"


}


