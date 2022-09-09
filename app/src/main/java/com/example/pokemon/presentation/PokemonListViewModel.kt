package com.example.pokemon.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.data.network.model.PokemonApiResponseNetworkEntity
import com.example.pokemon.domain.Repository
import com.example.pokemon.domain.model.PokeResult
import com.example.pokemon.utils.fromNetworkModelToRoomModel
import com.example.pokemon.utils.pokeResultFromDomainModelToRoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _pokeResult = MutableLiveData<List<PokeResult>>()
    val pokeResult: MutableLiveData<List<PokeResult>> = _pokeResult


    fun getData() {
        compositeDisposable.add(
            repository.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<PokemonApiResponseNetworkEntity>() {
                    override fun onSuccess(result: PokemonApiResponseNetworkEntity) {
                        viewModelScope.launch(Dispatchers.IO) {
                            repository.insertAllPokemon(
                                result.results.fromNetworkModelToRoomModel()

                            )
                            getDataFromRoom()
                        }
                    }

                    override fun onError(result: Throwable) {
                        result.printStackTrace()
                    }
                }
                )
        )
    }

    fun getDataFromRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPokemonList()
            _pokeResult.postValue(result.pokeResultFromDomainModelToRoomModel())
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}


