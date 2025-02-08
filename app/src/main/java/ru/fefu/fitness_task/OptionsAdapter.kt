package ru.fefu.fitness_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class OptionsAdapter(private val options: List<String>) : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    private var selectedPosition = -1
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionButton: Button = itemView.findViewById(R.id.optionButton)

        init {
            optionButton.setOnClickListener {
                val previousSelected = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousSelected)
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_option, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.optionButton.text = options[position]


        if (position == selectedPosition) {
            holder.optionButton.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.button_selected)
        } else {
            holder.optionButton.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.button_selector)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
}