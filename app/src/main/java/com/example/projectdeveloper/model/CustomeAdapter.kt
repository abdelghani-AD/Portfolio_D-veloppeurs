package com.example.projectdeveloper.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdeveloper.R

class CustomAdapter(private var context: Context, private var dataList: List<Modele>, private var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    constructor(context: Context, dataList: ArrayList<Modele>) : this(context,dataList ,onItemClickListener = object : OnItemClickListener{
        override fun onItemClick(modeleRecyclerView: Modele) {
            // Gérer l'action de clic nécessaire
        }
    })
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val langage: TextView = itemView.findViewById(R.id.langage)
        val developper: TextView = itemView.findViewById(R.id.developper)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val btnMore:Button = itemView.findViewById(R.id.btnMore)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.myreceyclerview, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modeleRecyclerView: Modele = dataList.get(position)
        holder.langage.text = modeleRecyclerView.langage
        holder.developper.text = modeleRecyclerView.developper
        holder.imageView.setImageResource(modeleRecyclerView.image)
        holder.btnMore.setOnClickListener {
            onItemClickListener.onItemClick(modeleRecyclerView)
        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}

