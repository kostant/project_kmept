package com.example.projectkmept.duryagin.duryagin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R

class duryagin_Adapter: RecyclerView.Adapter <DuryaginViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuryaginViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_duryagin,parent, false)
        return DuryaginViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: DuryaginViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}