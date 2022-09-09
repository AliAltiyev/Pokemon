package com.example.pokemon.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.data.network.model.PokemonNetworkEntity
import com.example.pokemon.domain.Repository
import com.example.pokemon.domain.model.Pokemon
import com.example.pokemon.utils.fromRoomModelToDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonInfo: MutableLiveData<Pokemon> = _pokemonInfo

    fun saveDataToRoom(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPokemon(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PokemonNetworkEntity>() {
                    override fun onSuccess(result: PokemonNetworkEntity) {
                        viewModelScope.launch(Dispatchers.IO) {
                            repository.insertPokemon(result.fromRoomModelToDomainModel())
                            getPokemonFromRoom(id)
                        }
                    }

                    override fun onError(result: Throwable) {
                        result.printStackTrace()
                    }
                })
        }
    }

    private fun getPokemonFromRoom(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPokemonById(id)
            pokemonInfo.postValue(
                result.fromRoomModelToDomainModel()
            )
        }

    }

}