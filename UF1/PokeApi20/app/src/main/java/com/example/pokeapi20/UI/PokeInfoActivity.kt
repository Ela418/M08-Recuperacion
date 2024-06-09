package com.example.pokeapi20.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapi20.data.ApiClient
import com.example.pokeapi20.data.PokemonDetail
import com.example.pokeapi20.databinding.ActivityPokeInfoBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("POKEMON_ID", -1)

        if (id != -1) {
            ApiClient.pokeApiService.getPokemonInfo(id).enqueue(object : Callback<PokemonDetail> {
                override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                    if (response.isSuccessful) {
                        response.body()?.let { pokemon ->
                            binding.nameTextView.text = pokemon.name.capitalize()
                            Picasso.get().load(pokemon.sprites.front_default).into(binding.imageView)
                            binding.heightText.text = "Altura: ${pokemon.height / 10.0}m"
                            binding.weightText.text = "Peso: ${pokemon.weight / 10.0}kg"
                            binding.typeText.text = "Tipo: ${pokemon.types.joinToString { it.type.name }}"
                            // Add more details if needed
                        }
                    }
                }

                override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}