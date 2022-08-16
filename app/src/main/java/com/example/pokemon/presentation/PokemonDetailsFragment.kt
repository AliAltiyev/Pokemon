package com.example.pokemon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.disnayland.presentation.adapter.setImageFromUrl
import com.example.pokemon.databinding.PokemonDetailsFragmentBinding


class PokemonDetailsFragment : Fragment() {
    private var uuid = 0
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