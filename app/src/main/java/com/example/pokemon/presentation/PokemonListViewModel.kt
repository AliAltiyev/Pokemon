package com.example.pokemon.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.data.NetworkController
import com.example.pokemon.domain.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PokemonListViewModel : ViewModel() {

    val pokemonList = MutableLiveData<List<Pokemon>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val disposables = CompositeDisposable()
    private val apiService = NetworkController()

    fun refreshData() {
        getApi()
    }


    private fun getApi() {

        disposables.add(


            apiService.getData().subscribeOn(
                Schedulers.io()
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Pokemon>>() {
                    override fun onSuccess(result: List<Pokemon>) {
                        result.let {
                            pokemonList.value = it
                            error.value = false
                            loading.value = false
                        }

                    }

                    override fun onError(e: Throwable) {

                        error.value = true
                        loading.value = true

                    }


                })


        )


    }


}