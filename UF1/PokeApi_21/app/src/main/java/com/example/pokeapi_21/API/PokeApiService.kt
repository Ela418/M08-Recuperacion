package com.example.pokeapi_21.API

import com.example.pokeapi_21.Models.PokeRespuesta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Definición del servicio de la API de Pokémon
interface PokeApiService {
    @GET("pokemon")
    fun obtenerListaPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<PokeRespuesta> // Método para obtener la lista de Pokémon con limit y offset
}

/*
public interface PokeApiService {
    @GET("pokemon")
    Call<PokeRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
}

 */