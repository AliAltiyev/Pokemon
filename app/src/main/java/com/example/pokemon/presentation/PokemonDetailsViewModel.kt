package com.example.pokemon.presentation

import android.app.Application
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.networking.NetworkController
import com.example.pokemon.databinding.PokemonDetailsFragmentBinding
import com.example.pokemon.domain.PokemonInfoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(application: Application) : BaseViewModel(application) {

    val pokemonInfo = MutableLiveData<PokemonInfoModel>()
    private val apiService = NetworkController()

    fun getPokemonInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.getPokemon(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    pokemonInfo.postValue(it)
                }

            } else {
                val view =
                    PokemonDetailsFragmentBinding
                        .inflate(LayoutInflater.from(getApplication<Application?>().applicationContext))

                view.pokemonHeightTxt.visibility = android.view.View.GONE
                view.itemImageView.visibility = android.view.View.GONE
                view.pokemonTypeTxt.visibility = android.view.View.GONE
                view.pokemonWeightTxt.visibility = android.view.View.GONE
                view.pokemonNameTxt.text = ERRO_MESSAGE
            }

        }
    }

    companion object {
        const val ERRO_MESSAGE = "Error! Make sure you are connected to the internet"
    }


}