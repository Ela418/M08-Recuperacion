package com.example.pokeapi_21

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokeapi_21.Models.Pokemon

// Definición del adaptador para el RecyclerView
class PokemonAdapter(private val context: Context) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val dataset: ArrayList<Pokemon> = ArrayList() // Lista de Pokémon

    // Crear una nueva vista (invocada por el layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    // Reemplazar el contenido de una vista (invocada por el layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = dataset[position]
        holder.nombreTextView.text = p.name

        // Usar Glide para cargar la imagen del Pokémon
        Glide.with(context)
            .load("http://pokeapi.co/media/sprites/pokemon/${p.number}.png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.fotoImageView)

        // Configurar el clic del botón
        holder.saveButton.setOnClickListener {
            savePokemonId(p.number)
        }
    }

    // Retornar el tamaño del dataset (invocada por el layout manager)
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Método para actualizar la lista de Pokémon
    fun crearListaPokemon(listaPokemon: ArrayList<Pokemon>) {
        dataset.addAll(listaPokemon)
        notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }

    private fun savePokemonId(id: Int) {
        val sharedPreferences = context.getSharedPreferences("pokemon_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Obtener la lista actual de IDs guardados
        val savedIds = sharedPreferences.getStringSet("saved_ids", mutableSetOf()) ?: mutableSetOf()

        // Añadir el nuevo ID a la lista
        savedIds.add(id.toString())

        // Guardar la lista actualizada
        editor.putStringSet("saved_ids", savedIds)
        editor.apply()
    }


    // Proveer una referencia a los views para cada dato (cada item de datos es solo un objeto de ViewHolder)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fotoImageView: ImageView = itemView.findViewById(R.id.fotoImageView)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val saveButton: Button = itemView.findViewById(R.id.saveButton) // Añadido botón
    }
}



/*
import android.view.ViewGroup

class PokemonAdapter(mainActivity: MainActivity) extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;

    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    public PokemonAdapter() {
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater,from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(positon);
        holder.nombreTextView.setText(p.getName());

        Glide.with(context)
            .load("http://pokeapi.co/media/sprites/pokemon/" + p.getNumber() p".png")
            .centerCrop()
            .crossFAde()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void crearListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView nombreTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.layout.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.layout.nombreTextView);
        }
    }
}
 */