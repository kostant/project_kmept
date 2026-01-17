package com.example.projectkmept.romaniuc.Romaniuc

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.projectkmept.R

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
    val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
    val followButton: Button = itemView.findViewById(R.id.followButton)

}