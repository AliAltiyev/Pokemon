package com.example.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.pokemon.databinding.PokemonItemForRecyclerBinding
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.utils.download

class PokemonListAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {

        val view = PokemonItemForRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        holder.binding.itemImageView.download(
            pokemonList[position].results[position].url,
            CircularProgressDrawable(holder.itemView.context)
        )
        holder.binding.textView.text = pokemonList[position].results[position].name

    }

    override fun getItemCount() = pokemonList.size
}