package com.example.pokemon.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.pokemon.R
import com.example.pokemon.databinding.PokemonListFragmentBinding
import com.example.pokemon.presentation.adapter.PokemonListAdapter
import com.example.pokemon.utils.observeOnce
import com.example.pokemon.utils.viewBinding
import com.yonder.statelayout.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment(R.layout.pokemon_list_fragment) {

    private val binding by viewBinding(PokemonListFragmentBinding::bind)
    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var pokemonAdapter: PokemonListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getDataFromApi()
        viewModel.getDataFromRoom()
        observe()

    }


    private fun observe() = with(binding) {
        viewModel.pokeResult.observe(viewLifecycleOwner) { result ->
            if (result.isNotEmpty()) {
                currentState.setState(State.CONTENT)
                pokemonAdapter.submitList(result)
            } else {
                currentState.setState(State.LOADING)
            }

        }

    }


    private fun initView() {
        with(binding.recyclerView) {
            pokemonAdapter = PokemonListAdapter { position ->
                val action =
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(position)
                findNavController().navigate(action)
            }
            adapter = pokemonAdapter

        }
    }

}