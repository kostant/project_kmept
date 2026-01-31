package com.example.projectkmept.romaniuc.Romaniuc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectkmept.R

class UsersAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserViewHolder>() {

    // 1. Обновленная модель данных под скриншот
    data class User(
        val authorName: String,
        val foodTitle: String,
        val category: String,
        val cookingTime: String,
        val avatarUrl: String,
        val foodImageUrl: String // Ссылка на картинку блюда
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Убедись, что R.layout.food — это тот XML с CardView, который мы обсуждали
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.food, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]

        holder.authorName.text = currentUser.authorName
        holder.foodTitle.text = currentUser.foodTitle
        holder.foodDetails.text = "${currentUser.category}  •  ${currentUser.cookingTime}"

        // Загрузка аватара автора
        Glide.with(holder.itemView.context)
            .load(currentUser.avatarUrl)
            .circleCrop() // Сделает аватар круглым программно
            .placeholder(R.drawable.avatar10) // Пока качается — покажем это
            .into(holder.avatarImageView)

        // Загрузка основной картинки блюда
        Glide.with(holder.itemView.context)
            .load(currentUser.foodImageUrl)
            .centerCrop()
            .into(holder.foodImageView)
    }


    override fun getItemCount(): Int = userList.size
}