package com.example.projectkmept.abdullin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R

class abdullinAdapter: RecyclerView.Adapter<abdullinViewHolder>() {
    val users = arrayOf("Кузнечик с мишенью в башне","Шарик любитель воперов","Штрых конские ляжки","ДИХЛОФОС АРСЕН",)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abdullinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_abdullin,parent, false)
        return  abdullinViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: abdullinViewHolder, position: Int) {
        val user = users[position]
        holder.nameTextView.text = user
    }
}