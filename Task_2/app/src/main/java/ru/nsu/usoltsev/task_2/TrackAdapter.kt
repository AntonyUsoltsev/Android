package ru.nsu.usoltsev.task_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TrackAdapter(private val tracks: List<Track>) : RecyclerView.Adapter<TrackViewHolder>() {
    private var viewCounter = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.track_item, parent, false)
        println("Create view $viewCounter")
        viewCounter++
        return TrackViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.trackTitle.text = track.title
        holder.trackArtist.text = track.artist
        holder.trackImage.setImageResource(track.imageResId)
    }

    override fun getItemCount() = tracks.size
}

