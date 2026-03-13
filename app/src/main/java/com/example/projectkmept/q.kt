package com.example.projectkmept

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.projectkmept.romaniuc.UsersAdapter

class RecipeDetailFragment : Fragment() {
    companion object {
        fun newInstance(user: UsersAdapter.User): RecipeDetailFragment {
            return RecipeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("authorName", user.authorName)
                    putString("foodTitle", user.foodTitle)
                    putString("category", user.category)
                    putString("cookingTime", user.cookingTime)
                    putString("avatarUrl", user.avatarUrl)
                    putString("foodImageUrl", user.foodImageUrl)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val foodImageView: ImageView = view.findViewById(R.id.foodImage)
        val authorAvatarView: ImageView = view.findViewById(R.id.authorAvatar)
        val authorNameView: TextView = view.findViewById(R.id.authorName)
        val foodTitleView: TextView = view.findViewById(R.id.foodTitle)
        val foodDetailsView: TextView = view.findViewById(R.id.foodDetails)
        val backButton: View = view.findViewById(R.id.backButton)

        arguments?.let { args ->
            val foodTitle = args.getString("foodTitle", "")
            val authorName = args.getString("authorName", "")
            val category = args.getString("category", "")
            val cookingTime = args.getString("cookingTime", "")
            val avatarUrl = args.getString("avatarUrl", "")
            val foodImageUrl = args.getString("foodImageUrl", "")

            foodTitleView.text = foodTitle
            authorNameView.text = authorName
            foodDetailsView.text = "$category • $cookingTime"

            Glide.with(this)
                .load(foodImageUrl)
                .centerCrop()
                .into(foodImageView)

            Glide.with(this)
                .load(avatarUrl)
                .circleCrop()
                .into(authorAvatarView)
        }

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}