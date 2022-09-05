package com.example.pokemon.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.base.BaseViewModel
import com.example.pokemon.data.data.db.MainLocaleData
import com.example.pokemon.data.data.network.MainRemoteData
import com.example.pokemon.domain.PokeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    application: Application,
    private val mainRemoteData: MainRemoteData,
    private val mainLocaleData: MainLocaleData

) : BaseViewModel(application) {


    val resultList: LiveData<List<PokeResult>> = mainLocaleData.getAllPokemon().asLiveData()

     fun getDataFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRemoteData.getData()
            if (response.isSuccessful) {
                response.body()?.results.let { pokeResult ->
                    if (pokeResult != null) {
                        mainLocaleData.insertAllPokemon(pokeResult)
                    }
                }
            }
        }

    }


}