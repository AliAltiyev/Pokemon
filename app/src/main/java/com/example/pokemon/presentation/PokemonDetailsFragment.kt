package com.example.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.disnayland.presentation.adapter.setImageFromUrl
import com.example.pokemon.R
import com.example.pokemon.databinding.PokemonDetailsFragmentBinding
import com.example.pokemon.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment(R.layout.pokemon_details_fragment) {
    private var uuid = 0
    private val binding by viewBinding(PokemonDetailsFragmentBinding::bind)

    private val viewModel: PokemonDetailsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            uuid = PokemonDetailsFragmentArgs.fromBundle(it!!).pokemonId
        }

        viewModel.getPokemonInfo(uuid)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.pokemonInfo.observe(viewLifecycleOwner) { pokemonModel ->
            if (pokemonModel != null) {
                binding.run {
                    pokemonNameTxt.text = pokemonModel.name
                    pokemonHeightTxt.text = pokemonModel.height.toString()
                    pokemonWeightTxt.text = pokemonModel.weight.toString()
                    pokemonTypeTxt.text = pokemonModel.id.toString()
                    itemImageView.setImageFromUrl(
                        pokemonModel.sprites.frontDefault,
                        circularProgressDrawable = CircularProgressDrawable(requireContext())
                    )
                }
            }
        }
    }


}