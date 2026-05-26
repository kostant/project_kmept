package com.example.projectkmept.Abdullin228

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectkmept.R

class UsersAdapter(private val allUsers: MutableList<UserCard>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var filteredUsers: MutableList<UserCard> = allUsers.toMutableList()

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImg: ImageView = view.findViewById(R.id.avatarImageView)
        val nameTxt: TextView = view.findViewById(R.id.nameTextView)
        val photoImg: ImageView = view.findViewById(R.id.photoImageView)
        val titleTxt: TextView = view.findViewById(R.id.titleTextView)
        val metaTxt: TextView = view.findViewById(R.id.metaTextView)
        val likeBtn: CardView = view.findViewById(R.id.likeButton)
        val likeTxt: TextView = view.findViewById(R.id.likeCountText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = filteredUsers[position]

        holder.nameTxt.text = user.userName
        holder.titleTxt.text = user.foodTitle
        holder.metaTxt.text = user.cookingTime
        holder.likeTxt.text = "${user.likes}"

        val ctx = holder.itemView.context
        if (user.isFollowed) {
            holder.likeBtn.setCardBackgroundColor(ctx.getColor(R.color.green))
            holder.likeTxt.setTextColor(ctx.getColor(android.R.color.white))
        } else {
            holder.likeBtn.setCardBackgroundColor(ctx.getColor(R.color.likeBackground))
            holder.likeTxt.setTextColor(ctx.getColor(R.color.green))
        }

        Glide.with(ctx).load(user.avatarUrl).circleCrop().into(holder.avatarImg)
        Glide.with(ctx).load(user.foodImageUrl).centerCrop().into(holder.photoImg)

        holder.likeBtn.setOnClickListener {
            user.isFollowed = !user.isFollowed
            user.likes += if (user.isFollowed) 1 else -1
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = filteredUsers.size

    /** Фильтрация по запросу — ищет по названию блюда, имени автора и описанию */
    fun filter(query: String) {
        filteredUsers = if (query.isBlank()) {
            allUsers.toMutableList()
        } else {
            val q = query.trim().lowercase()
            allUsers.filter { user ->
                user.foodTitle.lowercase().contains(q) ||
                user.userName.lowercase().contains(q) ||
                user.description.lowercase().contains(q) ||
                user.cookingTime.lowercase().contains(q)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }

    /** Добавить новое блюдо — оно сразу попадёт в список и будет искаться */
    fun addUser(user: UserCard) {
        allUsers.add(user)
        filteredUsers.add(user)
        notifyItemInserted(filteredUsers.size - 1)
    }

    fun getFilteredCount(): Int = filteredUsers.size
    fun getTotalCount(): Int = allUsers.size
}
