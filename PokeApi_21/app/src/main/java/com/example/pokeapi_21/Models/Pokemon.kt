package com.example.pokeapi_21.Models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class Pokemon {
    var name: String? = null // Nombre del Pokémon
    var url: String? = null // URL del Pokémon

    // Propiedad calculada para obtener el número del Pokémon desde la URL
    val number: Int
        get() {
            val urlParts = url?.split("/") ?: return -1
            return urlParts.last().toIntOrNull() ?: -1
        }
}