package com.example.pokeapi20.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import retrofit2.http.*
import com.example.pokeapi20.data.Pokemon

interface PokeService {

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset : Int): Call<PokeRespuesta>

    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int): Call<Pokemon>
}