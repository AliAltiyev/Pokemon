package com.example.pokemon.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.NetworkController
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    val pokemon = MutableLiveData<Pokemon>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val apiService = NetworkController()

    fun refreshData() {
        getApi()
    }


//    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        throwable.printStackTrace()
//    }

    private fun getApi() {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = apiService.getData()
            if (response.isSuccessful) {
                pokemon.postValue(response.body())
                error.postValue(false)
                loading.postValue(false)
            } else {
                error.value = true
                loading.value = true
            }
        }

    }
}