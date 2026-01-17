package com.example.projectkmept.romaniuc.Romaniuc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R

class UsersAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserViewHolder>() {

    // Обновленная модель данных
    data class User(
        val name: String,
        val timeInfo: String, // "now following you · 1h"
        val avatarResId: Int,
        var isFollowing: Boolean = false // состояние кнопки
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_romaniuc, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]

        // Устанавливаем данные
        holder.nameTextView.text = currentUser.name
        holder.timeTextView.text = currentUser.timeInfo
        holder.avatarImageView.setImageResource(currentUser.avatarResId)

        // Настраиваем кнопку Follow
        if (currentUser.isFollowing) {
            holder.followButton.text = "Followed"
            holder.followButton.backgroundTintList =
                android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#CCCCCC")
                )
        } else {
            holder.followButton.text = "Follow"
            holder.followButton.backgroundTintList =
                android.content.res.ColorStateList.valueOf(
                    android.graphics.Color.parseColor("#1FCC79")
                )
        }

        // Обработка клика по кнопке Follow
        holder.followButton.setOnClickListener {
            // Здесь можно обновить состояние в данных
             currentUser.isFollowing = !currentUser.isFollowing
             notifyItemChanged(position)

            println("Follow clicked for: ${currentUser.name}")
        }

        // Обработка клика по всей ячейке
        holder.itemView.setOnClickListener {
            println("Clicked: ${currentUser.name}")
        }
    }

    override fun getItemCount(): Int = userList.size
}