package com.example.pokeapi_21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi_21.API.PokeApiService
import com.example.pokeapi_21.Models.PokeRespuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Definición de la actividad principal
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "POKEDEX" // Etiqueta para los logs
    }

    private var retrofit: Retrofit? = null // Variable para Retrofit
    private lateinit var recyclerView: RecyclerView // RecyclerView para mostrar los Pokémon
    private lateinit var pokemonAdapter: PokemonAdapter // Adaptador para el RecyclerView

    private var offset = 0 // Desplazamiento para la paginación
    private var aptoParaCargar = true // Flag para controlar la carga de datos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización del RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        pokemonAdapter = PokemonAdapter(this)
        recyclerView.adapter = pokemonAdapter
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager

        // Listener para detectar el desplazamiento en el RecyclerView
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Si se desplaza hacia abajo
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    // Comprobar si se puede cargar más datos
                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, "Llegamos al final")

                            aptoParaCargar = false
                            offset += 20
                            obtenerDatos(offset) // Obtener más datos
                        }
                    }
                }
            }
        })

        // Inicialización de Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl("http://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        aptoParaCargar = true
        offset = 0
        obtenerDatos(offset) // Obtener los primeros datos
    }

    // Método para obtener datos de la API
    private fun obtenerDatos(offset: Int) {
        val service = retrofit?.create(PokeApiService::class.java)
        val pokeRespuestaCall = service?.obtenerListaPokemon(20, offset)

        pokeRespuestaCall?.enqueue(object : Callback<PokeRespuesta> {
            override fun onResponse(call: Call<PokeRespuesta>, response: Response<PokeRespuesta>) {
                aptoParaCargar = true
                if (response.isSuccessful) {
                    val pokeRespuesta = response.body()
                    val listaPokemon = pokeRespuesta?.results

                    // Actualizar el adaptador con la nueva lista de Pokémon
                    listaPokemon?.let { pokemonAdapter.crearListaPokemon(it) }
                } else {
                    Log.e(TAG, "onResponse: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<PokeRespuesta>, t: Throwable) {
                aptoParaCargar = true
            }
        })
    }
}

/*
class MainActivity : AppCompatActivity() {

    private static final String TAG = "POKEDEX";

    private var retrofit: Retrofit? = null

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    private int offset;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = (RecyclerView) findViewById(R.layout.recyclerView);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onSCrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0) {
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if(aptoParaCargar) {
                    if((visibleItemCount + pastVisibleItems) >=  totalItemCount) {
                        Log.i(TAG, " Llegamos al final");

                        aptoParaCargar = false;
                        offset += 20;
                        obtenerDatos(offset);
                    }
                }
            }
        }

        retrofit = new Retrofit.Builder()
            .baseUrl("http://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())

        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset){
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokeRespuesta> pokeRespuestaCall = service.obtenerListaPokemon(20 , offset);

        pokeRespuestaCall.enqueue(new CallBack<PokeRespuesta>() {
            @Override
            public void onResponse(Call<PokeRespuesta> call, Response<PokeRespuesta> response) {
            aptoParaCargar = true;
                if(response.isSucessful()) {
                    PokeRespuesta pokeRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokeRespuesta.getResults();

                    pokemonAdapter.crearListaPokemon(listaPokemon);
                }
                else {
                    Log.e(TAG, " onResponse: " + response.errorBody())
                }

            }

            @Override
            public void onFailure(Call<PokeRespuesta> call, Throwable t) {
                aptoParaCargar = true;
            }
        }
    }
}

*/