package ru.nsu.usoltsev.task_3.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView: TextView = view.findViewById(android.R.id.text1)
    val valueTextView: TextView = view.findViewById(android.R.id.text2)
}