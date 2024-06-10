package com.example.reproductorv10

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private val songs: List<Song>) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songTitleTextView: TextView = itemView.findViewById(R.id.songTitleTextView)
        val playButton: Button = itemView.findViewById(R.id.playButton)
        val pauseButton: Button = itemView.findViewById(R.id.pauseButton)
        val stopButton: Button = itemView.findViewById(R.id.stopButton)
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        holder.songTitleTextView.text = song.title

        holder.playButton.setOnClickListener {
            if (holder.mediaPlayer == null) {
                holder.mediaPlayer = MediaPlayer.create(holder.itemView.context, song.resourceId)
            }
            holder.mediaPlayer?.start()
        }

        holder.pauseButton.setOnClickListener {
            holder.mediaPlayer?.pause()
        }

        holder.stopButton.setOnClickListener {
            holder.mediaPlayer?.stop()
            holder.mediaPlayer?.release()
            holder.mediaPlayer = null
        }
    }
}

/*
package com.example.reproductorv10

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private val songs: List<Song>) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songTitleTextView: TextView = itemView.findViewById(R.id.songTitleTextView)
        val playButton: Button = itemView.findViewById(R.id.playButton)
        val pauseButton: Button = itemView.findViewById(R.id.pauseButton)
        val stopButton: Button = itemView.findViewById(R.id.stopButton)
        var mediaPlayer: MediaPlayer? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        holder.songTitleTextView.text = song.title

        holder.playButton.setOnClickListener {
            if (holder.mediaPlayer == null) {
                holder.mediaPlayer = MediaPlayer.create(holder.itemView.context, song.resourceId)
            }
            holder.mediaPlayer?.start()
        }

        holder.pauseButton.setOnClickListener {
            holder.mediaPlayer?.pause()
        }

        holder.stopButton.setOnClickListener {
            holder.mediaPlayer?.stop()
            holder.mediaPlayer?.release()
            holder.mediaPlayer = null
        }
    }
}
 */