package com.example.projectkmept.Romaniuc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R

class Romaniuc_Adapter: RecyclerView.Adapter<Romaniuc_ViewHolder>() {
    val users = arrayOf("2313123","sadsadfgsfgdas","pidarasina999")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Romaniuc_ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_romaniuc, parent, false )
        return Romaniuc_ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: Romaniuc_ViewHolder, position: Int) {
        val user = users[position]
        holder.nameTextView.text = user
    }

}