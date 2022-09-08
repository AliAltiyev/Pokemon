package com.example.pokemon.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.data.RepositoryImpl
import com.example.pokemon.data.data.db.EntityMapper
import com.example.pokemon.data.data.db.model.PokemonRoomEntity
import com.example.pokemon.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl,
    private val mapper: EntityMapper
) : ViewModel() {

    private val _pokemonInfo = MutableLiveData<PokemonRoomEntity>()
    val pokemonInfo: MutableLiveData<PokemonRoomEntity> = _pokemonInfo

    fun saveDataToRoom(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.getPokemon(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Pokemon>() {
                    override fun onSuccess(result: Pokemon) {
                        viewModelScope.launch(Dispatchers.IO) {
                            repositoryImpl.insertPokemon(mapper.fromDomainModelToRoomModel(result))
                        }
                    }

                    override fun onError(result: Throwable) {
                        result.printStackTrace()
                    }

                })
        }
    }


    fun getPokemonFromRoom(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repositoryImpl.getPokemonById(id)
            pokemonInfo.postValue(
                result
            )

        }

    }

}