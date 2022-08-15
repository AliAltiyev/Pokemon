package com.example.pokemon.presentation

import com.example.pokemon.data.networking.NetworkController
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.PokemonInfoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailsViewModel : ViewModel() {

    val pokemonInfo = MutableLiveData<PokemonInfoModel>()
    private val apiService = NetworkController()

     fun getPokemonInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.getPokemon(id)
            if (response.isSuccessful) {
                pokemonInfo.postValue(response.body())

            } else {
                response.message()
            }
        }

    }

}