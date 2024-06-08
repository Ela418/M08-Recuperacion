package com.example.pokeapi20.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi20.databinding.PokeListBinding
import com.example.pokeapi20.data.Pokemon

class PokeAdapter(private val onClick: (Int) -> Unit) : RecyclerView.Adapter<PokeAdapter.PokemonViewHolder>() {

    private var pokemonList: List<Pokemon> = emptyList()

    fun setData(newPokemonList: List<Pokemon>) {
        pokemonList = newPokemonList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position], onClick)
    }

    override fun getItemCount(): Int = pokemonList.size

    class PokemonViewHolder(private val binding: PokeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon, onClick: (Int) -> Unit) {
            binding.pokemonText.text = "#${pokemon.id} - ${pokemon.name}"
            itemView.setOnClickListener {
                onClick(pokemon.id)
            }
        }
    }
}

/*
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi20.databinding.PokeListBinding
import com.example.pokeapi20.data.Pokemon
import com.example.pokeapi20.data.PokemonDiffCallback

class PokeAdapter(private val onItemClicked: (Int) -> Unit) : ListAdapter<Pokemon, PokeAdapter.PokeViewHolder>(PokemonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val binding = PokeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon, onItemClicked)
    }

    class PokeViewHolder(private val binding: PokeListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon, onItemClicked: (Int) -> Unit) {
            binding.pokemonText.text = "#${pokemon.id} - ${pokemon.name.capitalize()}"
            binding.root.setOnClickListener { onItemClicked(pokemon.id) }
        }
    }
}

 */
