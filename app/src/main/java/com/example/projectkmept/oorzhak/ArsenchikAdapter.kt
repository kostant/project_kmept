package com.example.projectkmept.oorzhak

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R

class ArsenchikAdapter: RecyclerView.Adapter<ArsenchikViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArsenchikViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_oorzhak, parent, false)
        return ArsenchikViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ArsenchikViewHolder, position: Int) {

    }
}