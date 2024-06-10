package com.example.reproductorv10

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SongAdapter
    private lateinit var mediaPlayer: MediaPlayer

    private val songs: List<Song> by lazy {
        generateSongList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = SongAdapter(songs)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mediaPlayer = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun generateSongList(): List<Song> {
        val songList = mutableListOf<Song>()
        val fileNames = arrayOf(
            "bnet_vs_errece_leyendas",
            "duki__papo",
            "duki_rusher_litkillah",
            "duki_vs_crow",
            "lit_killah__alfa",
            "lit_killah__ash",
            "lit_killah__manteca",
            "lit_killah_fmk__la_vi",
            "metalingustica_vs_teorema",
            "papo__fundacion",
            "papo__vegano",
            "replik__artistas",
            "replik_vs_cacha",
            "replik_vs_nacho",
            "replik_vs_papo__gordo_forro",
            "rip_la_mashon",
            "trueno__presentacion",
            "trueno_vs_nacho",
            "wos__la_sexualidad_del_boludo",
            "wos_vs_klan",
            "zasko__ex",
            "zasko_vs_gazir",
            "zticma_vs_kaiser"
        )

        for (fileName in fileNames) {
            val title = fileName.replace("_", " ")
            val resourceId = getResourceId(fileName)
            if (resourceId != 0) {
                songList.add(Song(title, resourceId))
            } else {
                Log.e("MainActivity", "Resource not found for file: $fileName")
            }
        }

        return songList
    }

    private fun getResourceId(fileName: String): Int {
        return resources.getIdentifier(fileName, "raw", packageName)
    }
}

/*
package com.example.reproductorv10

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SongAdapter
    private lateinit var mediaPlayer: MediaPlayer

    private val songs: List<Song> by lazy {
        generateSongList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = SongAdapter(songs)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mediaPlayer = MediaPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun generateSongList(): List<Song> {
        val songList = mutableListOf<Song>()
        val fileNames = arrayOf(
            "bnet_vs_errece_leyendas.m4a",
            "duki__papo.m4a",
            "duki_rusher_litkillah.m4a",
            "duki_vs_crow.m4a",
            "lit_killah__alfa.m4a",
            "lit_killah__ash.m4a",
            "lit_killah__manteca.m4a",
            "lit_killah_fmk__la_vi.m4a",
            "metalingustica_vs_teorema.m4a",
            "papo__fundacion.m4a",
            "papo__vegano.m4a",
            "replik__artistas.m4a",
            "replik_vs_cacha.m4a",
            "replik_vs_nacho.m4a",
            "replik_vs_papo__gordo_forro.m4a",
            "rip_la_mashon.m4a",
            "trueno__presentacion.m4a",
            "trueno_vs_nacho.m4a",
            "wos__la_sexualidad_del_boludo.m4a",
            "wos_vs_klan.m4a",
            "zasko__ex.m4a",
            "zasko_vs_gazir.m4a",
            "zticma_vs_kaiser.m4a"
        )

        for (fileName in fileNames) {
            val title = fileName.substring(0, fileName.lastIndexOf("."))
            val resourceId = getResourceId(fileName)
            if (resourceId != 0) {
                songList.add(Song(title, resourceId))
            }
        }

        return songList
    }

    private fun getResourceId(fileName: String): Int {
        return resources.getIdentifier(fileName, "raw", packageName)
    }
}

 */