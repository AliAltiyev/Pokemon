package com.example.pokemon.data.data

import com.example.pokemon.data.data.db.PokemonDao
import com.example.pokemon.data.data.db.model.PokeResultRoomEntity
import com.example.pokemon.data.data.db.model.PokemonRoomEntity
import com.example.pokemon.data.data.network.PokemonApi
import com.example.pokemon.data.data.network.model.PokemonApiResponseNetworkEntity
import com.example.pokemon.data.data.network.model.PokemonNetworkEntity
import com.example.pokemon.domain.Repository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

private const val POKEMON_LIMIT_SIZE = 50
private const val POKEMON_OFFSET_SIZE = 0

@Singleton
class RepositoryImpl
@Inject constructor(
    private val dao: PokemonDao,
    private val api: PokemonApi
) : Repository {


    override suspend fun insertAllPokemon(list: List<PokeResultRoomEntity>) {
        dao.insertAllPokemon(list)
    }

    override suspend fun insertPokemon(pokemon: PokemonRoomEntity) {
        dao.insertPokemon(pokemon)
    }

    override suspend fun getPokemonList(): List<PokeResultRoomEntity> {
        return dao.getPokemon()
    }

    override suspend fun getPokemonById(id: Int): PokemonRoomEntity {
        return dao.getPokemonById(id)
    }


    //Network
    override fun getPokemon(id: Int): Single<PokemonNetworkEntity> {
        return api.getPokemonInfo(id)
    }

    override fun getData(): Single<PokemonApiResponseNetworkEntity> {
        return api.getPokemonList(POKEMON_LIMIT_SIZE, POKEMON_OFFSET_SIZE)
    }
}