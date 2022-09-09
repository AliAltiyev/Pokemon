package com.example.pokemon.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentDetailsBinding
import com.example.pokemon.utils.setImageFromUrl
import com.example.pokemon.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

private const val STROKE_WIDTH = 5f
private const val CENTER_RADIUS = 30f

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<PokemonDetailsFragmentArgs>()
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val viewModel: PokemonDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        viewModel.saveDataToRoom(args.position)

    }

    private fun observe() = with(binding) {
        viewModel.pokemonInfo.observe(viewLifecycleOwner) { pokemonModel ->
                pokemonNameTxt.text = pokemonModel.name
                pokemonHeightTxt.text = getString(R.string.weight, pokemonModel.weight.toString())
                pokemonWeightTxt.text = getString(R.string.height, pokemonModel.height.toString())
                pokemonTypeTxt.text = getString(R.string.type, pokemonModel.id.toString())
                itemImageView.setImageFromUrl(
                    pokemonModel.sprites.frontDefault,
                    circularProgressDrawable = CircularProgressDrawable(requireContext()).apply {
                        strokeWidth = STROKE_WIDTH
                        centerRadius = CENTER_RADIUS
                        start()
                    })
            }
        }
    }




