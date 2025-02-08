package ru.fefu.fitness_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActivityAdapter(
    private val items: List<ActivityItem>,
    private val onItemClick: (ActivityItem.Activity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SECTION = 0
        private const val TYPE_ACTIVITY = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ActivityItem.Section -> TYPE_SECTION
            is ActivityItem.Activity -> TYPE_ACTIVITY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SECTION -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_section, parent, false)
                SectionViewHolder(view)
            }
            TYPE_ACTIVITY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_activity, parent, false)
                ActivityViewHolder(view, onItemClick)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ActivityItem.Section -> (holder as SectionViewHolder).bind(item)
            is ActivityItem.Activity -> (holder as ActivityViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sectionDate: TextView = itemView.findViewById(R.id.sectionDate)

        fun bind(section: ActivityItem.Section) {
            sectionDate.text = section.date
        }
    }

    class ActivityViewHolder(
        itemView: View,
        private val onItemClick: (ActivityItem.Activity) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val activityTitle: TextView = itemView.findViewById(R.id.activityTitle)
        private val activityDescription: TextView = itemView.findViewById(R.id.activityDescription)

        fun bind(activity: ActivityItem.Activity) {
            activityTitle.text = activity.title
            activityDescription.text = activity.description

            itemView.setOnClickListener {
                onItemClick(activity)
            }
        }
    }
}