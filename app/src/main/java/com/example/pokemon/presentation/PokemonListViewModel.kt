package com.example.pokemon.presentation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.database.PokemonDataBase
import com.example.pokemon.data.networking.NetworkController
import com.example.pokemon.domain.PokemonList
import com.example.pokemon.domain.Result
import com.example.pokemon.utils.CustomSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonListViewModel(application: Application) : BaseViewModel(application) {

    private var refreshTime = 0.1 * 60 * 1000 * 1000 * 1000L
    val resultList = MutableLiveData<List<Result>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val apiService = NetworkController()
    private val dao = PokemonDataBase(getApplication<Application>().applicationContext).pokemonDao()
    private val sharedPreferences = CustomSharedPreferences()





    fun refreshData() {
        val updateTime = sharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromRoom()
        } else {
            getApi()
        }

    }


    //    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        throwable.printStackTrace()
//    }
    private fun getApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.getData()
            if (response.isSuccessful) {
                savePokemonListToRoom(response.body()?.results, response)
            } else {
                error.value = true
                loading.value = true
            }
        }

    }


    private fun savePokemonListToRoom(list: List<Result>?, response: Response<PokemonList>) {
        launch {
            list.let {
                dao.deleteAllPokemonResult(list = list!!)
                val resultListLong = dao.insertAllResult(*list.toTypedArray()) //list -> individual
                var i = 0
                while (i < list.size) {
                    list[i].uuid = resultListLong[i].toInt()
                    i++
                }
                showPokemonList(response)

            }


        }
        sharedPreferences.saveTime(System.nanoTime())


    }

    private fun showPokemonList(response: Response<PokemonList>) {
        resultList.postValue(response.body()?.results!!)
        error.postValue(false)
        loading.postValue(false)
    }

    private fun showPokemonListFromRoom(list: List<Result>) {
        resultList.postValue(list)
        error.postValue(false)
        loading.postValue(false)
    }

    private fun getDataFromRoom() {
        launch {
            val pokemonList = dao.getAllPokemonResultList()
            showPokemonListFromRoom(pokemonList)
            Toast.makeText(getApplication(), DATA_FROM_ROOM, Toast.LENGTH_LONG).show()
        }
    }


    fun deletePokemonFromRoom(result: Result) {
        launch {
            dao.deletePokemonResult(result)
        }
    }

    companion object {


        private const val DATA_FROM_ROOM = "Data from Room"
        private const val DATA_FROM_API = "Data from API"

    }
}