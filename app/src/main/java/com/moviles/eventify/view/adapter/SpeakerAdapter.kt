package com.moviles.eventify.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.eventify.R
import com.moviles.eventify.model.Speaker

class SpeakerAdapter() : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>(){
    var listSpeakers = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false))

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return listSpeakers.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSpeaker = itemView.findViewById<ImageView>(R.id.ivSpeaker)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvSpeakerName)
        val tvSpeakerJob = itemView.findViewById<TextView>(R.id.tvSpeakerJob)
    }
}