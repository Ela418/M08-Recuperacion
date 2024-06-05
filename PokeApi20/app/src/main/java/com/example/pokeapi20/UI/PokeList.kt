package com.example.pokeapi20.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi20.databinding.ActivityMainBinding
import com.example.pokeapi20.model.PokeAdapter
import com.example.pokeapi20.model.PokeInfoViewModel
import com.example.pokeapi20.model.PokeListViewModel

class PokeList: AppCompatActivity() {

    private lateinit var viewModel: PokeListViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokeAdapter: PokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokeListViewModel::class.java]

        initUI()
    }

    private fun initUI(){
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)

        pokeAdapter = PokeAdapter {
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        binding.pokelistRecyclerView.adapter = pokeAdapter

        viewModel.getPokemonList()
        viewModel.pokemonList.observe(this, Observer { list ->
            pokeAdapter.setData(list)
        })
    }
}