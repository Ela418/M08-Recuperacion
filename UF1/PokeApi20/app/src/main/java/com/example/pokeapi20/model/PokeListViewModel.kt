package com.example.pokeapi20.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapi20.data.ApiClient
import com.example.pokeapi20.data.Pokemon
import com.example.pokeapi20.data.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeListViewModel : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> get() = _pokemonList

    init {
        getPokemons()
    }

    fun getPokemons() {
        ApiClient.pokeApiService.getPokemonList().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { results ->
                        val pokemonList = results.map { result ->
                            val id = result.url.split("/").dropLast(1).last().toInt()
                            Pokemon(id, result.name.capitalize())
                        }
                        _pokemonList.value = pokemonList
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}

/*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapi20.data.ApiClient
import com.example.pokeapi20.data.PokemonResponse
import com.example.pokeapi20.data.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeListViewModel : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> get() = _pokemonList

    init {
        getPokemons()
    }

    fun getPokemons() {
        ApiClient.pokeApiService.getPokemonList().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { results ->
                        val pokemonList = results.map { result ->
                            val id = result.url.split("/").dropLast(1).last().toInt()
                            Pokemon(id, result.name.capitalize(), "", "", "", "", "", "")
                        }
                        _pokemonList.value = pokemonList
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}

 */