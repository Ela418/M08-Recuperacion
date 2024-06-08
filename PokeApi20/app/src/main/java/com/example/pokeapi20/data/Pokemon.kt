package com.example.pokeapi20.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String = "",
    val height: String = "",
    val weight: String = "",
    val type: String = "",
    val baseExperience: String = "",
    val description: String = ""
)


/*
data class Pokemon (
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("base_experience") val baseExperience: Int,
    @Expose @SerializedName("height") val height: Int,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("sprites") val sprites: Sprites,
    @Expose @SerializedName("types") val types: List<Type>,
    @Expose @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,

    var spanishFlavorTextEntries: List<String> = emptyList()
)

data class Type(
    @Expose @SerializedName("slot") val slot: Int,
    @Expose @SerializedName("type") val type: TypeDetail
)

data class TypeDetail(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class FlavorTextEntry(
    @Expose @SerializedName("flavor_text") val flavorText: String,
    @Expose @SerializedName("language") val language: Language
)

data class Language(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

data class Sprites(
    @Expose @SerializedName("back_default") val backDefault: String?,
    @Expose @SerializedName("back_female") val backFemale: String?,
    @Expose @SerializedName("back_shiny") val backShiny: String?,
    @Expose @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_female") val frontFemale: String?,
    @Expose @SerializedName("front_shiny") val frontShiny: String?,
    @Expose @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class PokeResult(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)

 */