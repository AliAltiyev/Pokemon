package com.example.pokemon.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pokemon.domain.model.PokeResult

class ItemDiffCallBack : DiffUtil.ItemCallback<PokeResult>() {

    override fun areItemsTheSame(oldItem: PokeResult, newItem: PokeResult): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PokeResult, newItem: PokeResult): Boolean {
        return oldItem == newItem
    }


}

