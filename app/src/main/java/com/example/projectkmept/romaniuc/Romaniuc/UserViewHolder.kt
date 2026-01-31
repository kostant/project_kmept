package com.example.projectkmept.romaniuc.Romaniuc

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.projectkmept.R

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
    val authorName: TextView = itemView.findViewById(R.id.authorName)
    val foodImageView: ImageView = itemView.findViewById(R.id.foodImageView)
    val foodTitle: TextView = itemView.findViewById(R.id.foodTitle)
    val foodDetails: TextView = itemView.findViewById(R.id.foodDetails)
}