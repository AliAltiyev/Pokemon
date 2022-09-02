package com.example.pokemon.presentation

import android.app.Application
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.base.BaseViewModel
import com.example.pokemon.data.networking.MainRemoteData
import com.example.pokemon.databinding.PokemonDetailsFragmentBinding
import com.example.pokemon.domain.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    application: Application,
    private val mainRemoteData: MainRemoteData
) : BaseViewModel(application) {

    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemonInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRemoteData.getPokemon(id)
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
                view.pokemonNameTxt.text = ERROR_MESSAGE
            }

        }
    }

    companion object {
        const val ERROR_MESSAGE = "Error! Make sure you are connected to the internet"
    }


}