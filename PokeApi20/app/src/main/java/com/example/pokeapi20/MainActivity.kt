package com.example.pokeapi20


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi20.model.PokeAdapter
import com.example.pokeapi20.UI.PokeInfoActivity
import com.example.pokeapi20.model.PokeInfoViewModel
import com.example.pokeapi20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

/*
    private lateinit var viewModel: PokeInfoViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokeAdapter: PokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

        initUI()
    }

    private fun initUI() {
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)

        pokeAdapter = PokeAdapter { pokemonId ->
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", pokemonId)
            startActivity(intent)
        }

        binding.pokelistRecyclerView.adapter = pokeAdapter

        viewModel.getPokemonList()
        viewModel.pokeInfo.observe(this, androidx.lifecycle.Observer { response ->
            response?.results?.let { pokeAdapter.setData(it) }
        })
    }
    */
}


    /*
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/docs/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}
     */