package com.example.projectkmept.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectkmept.R
import com.example.projectkmept.model.GridItem

class GridItemAdapter(
    private var items: List<GridItem>,
    private val onClick: (GridItem) -> Unit
) : RecyclerView.Adapter<GridItemAdapter.GridItemViewHolder>() {

    fun updateItems(newItems: List<GridItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid, parent, false)
        return GridItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    override fun getItemCount(): Int = items.size

    class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.itemTitle)
        private val subtitleText: TextView = itemView.findViewById(R.id.itemSubtitle)
        private val previewImage: ImageView = itemView.findViewById(R.id.itemImage)

        fun bind(item: GridItem, onClick: (GridItem) -> Unit) {
            titleText.text = item.title
            subtitleText.text = item.subtitle

            if (item.imageUrl.isNotBlank()) {
                previewImage.visibility = View.VISIBLE
                Glide.with(itemView)
                    .load(item.imageUrl)
                    .circleCrop()
                    .into(previewImage)
            } else {
                previewImage.visibility = View.GONE
            }

            itemView.setOnClickListener { onClick(item) }
        }
    }
}
