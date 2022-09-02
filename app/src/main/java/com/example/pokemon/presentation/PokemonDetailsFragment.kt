package com.example.pokemon.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.pokemon.R
import com.example.pokemon.databinding.PokemonDetailsFragmentBinding
import com.example.pokemon.utils.setImageFromUrl
import com.example.pokemon.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment(R.layout.pokemon_details_fragment) {

    private val args by navArgs<PokemonDetailsFragmentArgs>()
    private val binding by viewBinding(PokemonDetailsFragmentBinding::bind)

    private val viewModel: PokemonDetailsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPokemonInfo(args.position)
        observe()
    }

    private fun observe() = with(binding) {
        viewModel.pokemonInfo.observe(viewLifecycleOwner) { pokemonModel ->
            pokemonModel.let {
                pokemonNameTxt.text = pokemonModel.name
                pokemonHeightTxt.text = getString(R.string.weight, pokemonModel.weight.toString())
                pokemonWeightTxt.text = getString(R.string.height, pokemonModel.height.toString())
                pokemonTypeTxt.text = getString(R.string.type, pokemonModel.id.toString())
                itemImageView.setImageFromUrl(
                    pokemonModel.sprites.frontDefault,
                    circularProgressDrawable = CircularProgressDrawable(requireContext()).apply {
                        strokeWidth = 5f
                        centerRadius = 30f
                        start()
                    })
            }
        }
    }
        }



