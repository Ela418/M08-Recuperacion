package com.example.pokeapi20.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi20.data.PokeRespuesta
import com.example.pokeapi20.data.PokeResult
import com.example.pokeapi20.databinding.PokeListBinding

class PokeAdapter(private val pokemonClick: (Int) -> Unit) : RecyclerView.Adapter<PokeAdapter.SearchViewHolder>() {
    private var pokemonList: List<PokeResult> = emptyList()

    fun setData(list: List<PokeRespuesta>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = PokeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val binding = holder.binding
        val pokemon = pokemonList[position]

        binding.pokemonText.text = "${position + 1} - ${pokemon.name}"

        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    class SearchViewHolder(val binding: PokeListBinding) : RecyclerView.ViewHolder(binding.root)
}