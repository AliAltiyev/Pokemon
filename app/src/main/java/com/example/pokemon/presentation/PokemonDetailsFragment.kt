package com.example.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.databinding.PokemonDetailsFragmentBinding
import com.example.pokemon.presentation.adapter.PokemonListAdapter
import dagger.hilt.android.AndroidEntryPoint


class PokemonDetailsFragment : Fragment() {

    private lateinit var binding: PokemonDetailsFragmentBinding

    private lateinit var viewModel: PokemonDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = PokemonDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   viewModel = ViewModelProvider(this).get(PokemonDetailsViewModel::class.java)


//        viewModel.getPokemonInfo()

    }


}