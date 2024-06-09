package com.example.pokeapi20.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeService {
    @GET("pokemon/{id}/")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon-species/{id}/")
    fun getPokemonSpecies(@Path("id") id: Int): Call<Pokemon>
}

interface PokeApiService {

    @GET("pokemon?limit=1032")
    fun getPokemonList(): Call<PokemonResponse>

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<PokemonDetail>
}
