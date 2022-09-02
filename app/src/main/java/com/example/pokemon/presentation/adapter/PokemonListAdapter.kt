package com.example.pokemon.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.base.BaseAdapter
import com.example.pokemon.databinding.PokemonItemForRecyclerBinding
import com.example.pokemon.domain.PokeResult

class PokemonListAdapter(private val ItemClick: (Int) -> Unit) :
    BaseAdapter<PokeResult>(
        { oldItem, newItem -> oldItem.name == newItem.name },
        { oldItem, newItem -> oldItem == newItem }
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = PokemonItemForRecyclerBinding.inflate(inflater, parent, false)
        return PokemonItemViewHolder(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PokemonItemViewHolder -> {
                val item = getItem(position)
                holder.binding.heroName.text = item.name
                holder.itemView.setOnClickListener {
                    ItemClick(position + 1)
                }
            }

        }
    }
}
