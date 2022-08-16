package com.example.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.PokemonItemForRecyclerBinding
import com.example.pokemon.domain.Result
import com.example.pokemon.presentation.PokemonListFragmentDirections

class PokemonListAdapter() :
    ListAdapter<Result, PokemonItemViewHolder>(PokemonDiffCallBack()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {

            val view = PokemonItemForRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return PokemonItemViewHolder(view)

        }

        override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
            val item = getItem(position)

            holder.binding.textView.text = item.name
            holder.itemView.setOnClickListener { view ->
                val selectedItem = item.uuid
                val action =
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                        selectedItem
                    )

                Navigation.findNavController(view)
                    .navigate(action)
            }


        }

    }



