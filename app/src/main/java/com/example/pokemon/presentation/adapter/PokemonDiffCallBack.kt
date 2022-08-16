package com.example.pokemon.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pokemon.domain.Result

class PokemonDiffCallBack : DiffUtil.ItemCallback<Result>() {


    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem

    }
}

