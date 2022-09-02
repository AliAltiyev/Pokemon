package com.example.pokemon.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.base.BaseViewModel
import com.example.pokemon.data.networking.MainRemoteData
import com.example.pokemon.domain.PokeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    application: Application,
    private val mainRemoteData: MainRemoteData

) : BaseViewModel(application) {


    val resultList = MutableLiveData<List<PokeResult>>()


    fun getApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRemoteData.getData()
            if (response.isSuccessful) {
                response.body()?.results.let { resultList.postValue(it) }
            }
        }

    }
}