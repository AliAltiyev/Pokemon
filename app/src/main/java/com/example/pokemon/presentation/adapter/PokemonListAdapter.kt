package com.example.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.pokemon.databinding.PokemonItemForRecyclerBinding
import com.example.pokemon.domain.model.PokeResult

class PokemonListAdapter(private val ItemClick: (Int) -> Unit) :
    ListAdapter<PokeResult, PokemonItemViewHolder>(ItemDiffCallBack()) {
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
        holder.binding.pokemonName.text = item.name
        holder.itemView.setOnClickListener {
            ItemClick(position + 1)
        }
    }
}

