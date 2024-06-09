package com.example.pokeapi20

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi20.model.PokeAdapter
import com.example.pokeapi20.UI.PokeInfoActivity
import com.example.pokeapi20.model.PokeListViewModel
import com.example.pokeapi20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PokeListViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokeAdapter: PokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)
        viewModel.getPokemons()
        setupRecyclerView()

        viewModel.pokemonList.observe(this, Observer { pokemons ->
            pokeAdapter.setData(pokemons)
        })
    }

    private fun setupRecyclerView() {
        pokeAdapter = PokeAdapter { pokemonId ->
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("POKEMON_ID", pokemonId)
            startActivity(intent)
        }
        binding.pokelistRecyclerView.apply {
            adapter = pokeAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}