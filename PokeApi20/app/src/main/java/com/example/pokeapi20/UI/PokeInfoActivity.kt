package com.example.pokeapi20.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokeapi20.databinding.ActivityPokeInfoBinding
import com.example.pokeapi20.model.PokeInfoViewModel

class PokeInfoActivity: AppCompatActivity() {

    lateinit var viewModel: PokeInfoViewModel
    private lateinit var binding: ActivityPokeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

        initUI()
    }

    private fun initUI() {
        val id = intent.extras?.getInt("id") ?: return

        viewModel.getPokemonInfo(id)
        viewModel.pokeDescription.observe(this, Observer { pokemon ->
            val typeNames = pokemon.types.map { it.type.name }
            binding.nameTextView.text = pokemon.name
            binding.heightText.text = "Altura: \n${pokemon.height / 10.0}m"
            binding.weightText.text = "Peso: \n${pokemon.weight / 10.0}Kg"
            binding.typeText.text = "Tipo: \n${typeNames.joinToString()}"
            binding.expBaseText.text = "Exp base: \n${pokemon.baseExperience}"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.imageView)
        })

        viewModel.getPokeDescription(id)
        viewModel.pokeDescription.observe(this) { pokemon ->

            val spanishEntries = pokemon.flavorTextEntries.filter { it.language.name == "es" }

            val spanishText = "Descripción: \n" + spanishEntries.firstOrNull()?.flavorText

            binding.descriptionText.text = spanishText

            viewModel.pokeInfo.value?.spanishFlavorTextEntries =
                spanishEntries.map { it.flavorText }
        }
    }
}



/*

lateinit var viewModel: PokeInfoViewModel
private lateinit var binding: ActivityPokeInfoBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityPokeInfoBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProvider(this)[PokeInfoViewModel::class.java]

    initUI()
}

private fun initUI() {
    val id = intent.extras?.get("id") as Int

    viewModel.getPokeDescription(id)

    viewModel.pokeInfo.observe(this, Observer { pokemon ->
        val typeNames = pokemon.types.map { it.type.name }
        binding.nameTextView.text = pokemon.name
        binding.heightText.text = "Altura: \n${pokemon.height / 10.0}m"
        binding.weightText.text = "Peso: \n${pokemon.weight / 10.0}Kg"

        binding.typeText.text = "Tipo: \n${typeNames.joinToString()}"
        binding.expBaseText.text = "Exp base: \n${pokemon.baseExperience}"

        Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.imageView)
    })

    viewModel.getPokeDescription(id)
    viewModel.pokeDescription.observe(this) { pokemon ->

        val spanishEntries = pokemon.flavorTextEntries.filter { it.language.name == "es" }

        val spanishText = "Descripción: \n" + spanishEntries.firstOrNull()?.flavorText

        binding.descriptionText.text = spanishText

        viewModel.pokeInfo.value?.spanishFlavorTextEntries =
            spanishEntries.map { it.flavorText }
    }
}

*/