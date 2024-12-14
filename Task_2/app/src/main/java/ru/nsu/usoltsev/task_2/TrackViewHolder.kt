package ru.nsu.usoltsev.task_2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val trackTitle: TextView = itemView.findViewById(R.id.trackTitle)
    val trackArtist: TextView = itemView.findViewById(R.id.trackArtist)
    val trackImage: ImageView = itemView.findViewById(R.id.trackImage)
}
