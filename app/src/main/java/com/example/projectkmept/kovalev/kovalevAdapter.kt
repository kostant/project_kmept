package com.example.projectkmept.kovalev

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R

class kovalevAdapter: RecyclerView.Adapter<kovalevViewHolder>(){

    val users = arrayOf("Кирилл", "Шеф", "Дурягин", "Илиан")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kovalevViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_kovalev, parent, false)
        return kovalevViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 10

    }

    override fun onBindViewHolder(holder: kovalevViewHolder, position: Int) {
        val user = users[position]
        holder.nameTextNew.text = user
    }
}