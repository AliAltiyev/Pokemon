package com.example.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokemon.databinding.PokemonListFragmentBinding
import com.example.pokemon.presentation.adapter.PokemonListAdapter

class PokemonListFragment : Fragment() {

    private lateinit var binding: PokemonListFragmentBinding
    private lateinit var adapter: PokemonListAdapter
    private val viewModel by viewModels<PokemonListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()

        viewModel.pokemonList.observe(viewLifecycleOwner) {
            adapter = PokemonListAdapter(it)
            binding.recyclerView.adapter = adapter
        }

        //Checking error message
        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error == true) {
                binding.errorTxt.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE

            } else {
                binding.errorTxt.visibility = View.GONE

            }


        }

        //Checking loading message
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading == true) {
                binding.errorTxt.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE

            }
        }


    }
}