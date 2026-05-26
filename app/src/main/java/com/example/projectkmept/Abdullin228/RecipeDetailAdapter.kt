package com.example.projectkmept.Abdullin228

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectkmept.R

// ─── ViewHolders ─────────────────────────────────────────────────────────────

class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val foodTitle: TextView       = itemView.findViewById(R.id.foodTitle)
    val foodDetails: TextView     = itemView.findViewById(R.id.foodDetails)
    val authorAvatar: ImageView   = itemView.findViewById(R.id.authorAvatar)
    val authorName: TextView      = itemView.findViewById(R.id.authorName)
    val likeButton: LinearLayout  = itemView.findViewById(R.id.likeButton)
    val likeCircle: CardView      = itemView.findViewById(R.id.likeCircle)
    val likesCount: TextView      = itemView.findViewById(R.id.likesCount)
    val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
}

class SectionTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val sectionTitle: TextView = itemView.findViewById(R.id.sectionTitle)
}

class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ingredientRow: LinearLayout = itemView.findViewById(R.id.ingredientRow)
    val checkCircle: FrameLayout    = itemView.findViewById(R.id.checkCircle)
    val checkIcon: ImageView        = itemView.findViewById(R.id.checkIcon)
    val ingredientName: TextView    = itemView.findViewById(R.id.ingredientName)
}

class StepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val stepNumber: TextView      = itemView.findViewById(R.id.stepNumber)
    val stepInstruction: TextView = itemView.findViewById(R.id.stepInstruction)
    val stepImageCard: CardView   = itemView.findViewById(R.id.stepImageCard)
    val stepImage: ImageView      = itemView.findViewById(R.id.stepImage)
}

// ─── Adapter ─────────────────────────────────────────────────────────────────

class RecipeDetailAdapter(
    val items: MutableList<Item>,
    private val onBack: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_INFO          = 1
        const val TYPE_SECTION_TITLE = 2
        const val TYPE_INGREDIENT    = 3
        const val TYPE_STEP          = 4
    }

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is InfoItem         -> TYPE_INFO
        is SectionTitleItem -> TYPE_SECTION_TITLE
        is IngredientItem   -> TYPE_INGREDIENT
        is StepItem         -> TYPE_STEP
        else                -> 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inf = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_INFO          -> InfoViewHolder(inf.inflate(R.layout.item_info, parent, false))
            TYPE_SECTION_TITLE -> SectionTitleViewHolder(inf.inflate(R.layout.item_section_title, parent, false))
            TYPE_INGREDIENT    -> IngredientViewHolder(inf.inflate(R.layout.item_ingredient, parent, false))
            TYPE_STEP          -> StepViewHolder(inf.inflate(R.layout.item_step, parent, false))
            else               -> error("Неизвестный тип ячейки")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {

            is InfoItem -> {
                holder as InfoViewHolder
                val ctx = holder.itemView.context
                holder.foodTitle.text = item.name
                holder.foodDetails.text = item.cookingTime
                holder.authorName.text = item.authorName
                holder.descriptionText.text = item.description
                applyLikeState(holder, item)
                Glide.with(ctx).load(item.avatarUrl).circleCrop().into(holder.authorAvatar)
                holder.likeButton.setOnClickListener {
                    item.isLiked = !item.isLiked
                    item.likes += if (item.isLiked) 1 else -1
                    applyLikeState(holder, item)
                }
            }

            is SectionTitleItem -> {
                (holder as SectionTitleViewHolder).sectionTitle.text = item.title
            }

            is IngredientItem -> {
                holder as IngredientViewHolder
                holder.ingredientName.text = item.name
                applyCheckState(holder, item)
                holder.ingredientRow.setOnClickListener {
                    item.isChecked = !item.isChecked
                    applyCheckState(holder, item)
                }
            }

            is StepItem -> {
                holder as StepViewHolder
                holder.stepNumber.text = item.number.toString()
                holder.stepInstruction.text = item.instruction
                holder.stepImageCard.visibility = View.VISIBLE
                Glide.with(holder.itemView.context)
                    .load(item.image)
                    .centerCrop()
                    .placeholder(android.R.color.darker_gray)
                    .into(holder.stepImage)
            }
        }
    }

    private fun applyLikeState(holder: InfoViewHolder, item: InfoItem) {
        if (item.isLiked) {
            // Залайкано: более тёмный зелёный круг
            holder.likeCircle.setCardBackgroundColor(0xFF17A862.toInt())
        } else {
            // Обычно: зелёный #1FCC79
            holder.likeCircle.setCardBackgroundColor(0xFF1FCC79.toInt())
        }
        holder.likesCount.text = "${item.likes} Likes"
    }

    private fun applyCheckState(holder: IngredientViewHolder, item: IngredientItem) {
        if (item.isChecked) {
            holder.checkCircle.setBackgroundResource(R.drawable.bg_circle_checked)
            holder.checkIcon.visibility = View.VISIBLE
        } else {
            holder.checkCircle.setBackgroundResource(R.drawable.bg_circle_unchecked)
            holder.checkIcon.visibility = View.GONE
        }
        // Текст без зачёркивания
        holder.ingredientName.setTextColor(0xFF2E3E5C.toInt())
        holder.ingredientName.paintFlags =
            holder.ingredientName.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
