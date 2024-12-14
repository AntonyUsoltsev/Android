package ru.nsu.usoltsev.task_3.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nsu.usoltsev.task_3.model.Currency

class CurrencyAdapter(private val currencies: List<Currency>) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencies[position]
        holder.nameTextView.text = currency.Name
        holder.valueTextView.text = String.format("%.4f ₽", currency.NormalizeValue)
    }

    override fun getItemCount(): Int = currencies.size
}
