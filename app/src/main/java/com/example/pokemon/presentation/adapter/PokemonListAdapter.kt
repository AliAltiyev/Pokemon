package com.example.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.disnayland.presentation.adapter.setImageFromUrl
import com.example.pokemon.R
import com.example.pokemon.databinding.PokemonItemForRecyclerBinding
import com.example.pokemon.domain.Result
import com.example.pokemon.presentation.PokemonListFragmentDirections
import com.squareup.picasso.Picasso

class PokemonListAdapter() :
//    ListAdapter<Pokemon, PokemonItemViewHolder>(PokemonDiffCallBack())
    RecyclerView.Adapter<PokemonItemViewHolder>() {

    var resultList = listOf<Result>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {

        val view = PokemonItemForRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {


        holder.binding.textView.text = resultList[position].name
        holder.itemView.setOnClickListener { view ->
            val selectedItem = resultList[position]
            val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment()

        }


    }

    override fun getItemCount() = resultList.size


}


