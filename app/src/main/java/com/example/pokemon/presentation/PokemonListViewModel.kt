package com.example.pokemon.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.data.RepositoryImpl
import com.example.pokemon.data.data.db.EntityMapper
import com.example.pokemon.domain.model.PokeResult
import com.example.pokemon.domain.model.PokemonApiResponse
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
    private val repositoryImpl: RepositoryImpl,
    private val mapper: EntityMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _pokeResult = MutableLiveData<List<PokeResult>>()
    val pokeResult: MutableLiveData<List<PokeResult>> = _pokeResult


    fun getDataFromApi() {
        compositeDisposable.add(
            repositoryImpl.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    DisposableSingleObserver<PokemonApiResponse>() {
                    override fun onSuccess(result: PokemonApiResponse) {
                        viewModelScope.launch(Dispatchers.IO) {
                            repositoryImpl.insertAllPokemon(
                                mapper.pokeResultRoomEntityFromDomainModelToRoomModel(result.results)
                            )
                        }
                    }

                    override fun onError(error: Throwable) {
                        error.printStackTrace()
                    }
                })
        )
    }


    fun getDataFromRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repositoryImpl.getPokemonList()
            _pokeResult.postValue(mapper.pokeResultFromDomainModelToRoomModel(result))
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}