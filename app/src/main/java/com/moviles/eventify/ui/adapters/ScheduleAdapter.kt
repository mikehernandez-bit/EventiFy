package com.moviles.eventify.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.eventify.R
import com.moviles.eventify.data.model.ScheduleItem

class ScheduleAdapter(
    private val items: List<ScheduleItem>,
    private val onItemClick: (ScheduleItem) -> Unit
) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvHour: TextView = view.findViewById(R.id.tvItemScheduleHour)
        val tvAMPM: TextView = view.findViewById(R.id.tvItemScheduleAMPM)
        val tvTitle: TextView = view.findViewById(R.id.tvItemScheduleTitle)
        val tvSpeaker: TextView = view.findViewById(R.id.tvItemScheduleSpeaker)
        val tvPlace: TextView = view.findViewById(R.id.tvItemSchedulePlace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvHour.text = item.hour
        holder.tvAMPM.text = item.ampm
        holder.tvTitle.text = item.title
        holder.tvSpeaker.text = item.speaker
        holder.tvPlace.text = item.place

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
