package com.example.projectkmept.Abdullin228

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectkmept.R

class RecipeDetailFragment : Fragment() {

    companion object {
        fun newInstance(user: UserCard): RecipeDetailFragment {
            val fragment = RecipeDetailFragment()
            val args = Bundle()
            args.putString("u_name", user.userName)
            args.putString("f_title", user.foodTitle)
            args.putString("f_time", user.cookingTime)
            args.putString("a_url", user.avatarUrl)
            args.putString("f_url", user.foodImageUrl)
            args.putInt("likes", user.likes)
            args.putBoolean("liked", user.isFollowed)
            args.putString("description", user.description)
            args.putStringArrayList("ingredients", ArrayList(user.ingredients))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val heroImage: ImageView = view.findViewById(R.id.heroImage)
        val backBtn: View = view.findViewById(R.id.backButton)
        val recyclerView: RecyclerView = view.findViewById(R.id.detailRecyclerView)

        // RecyclerView внутри NestedScrollView — отключаем вложенный скролл
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val args = arguments ?: return
        val name         = args.getString("f_title", "")
        val description  = args.getString("description", "")
        val cookingTime  = args.getString("f_time", "")
        val avatarUrl    = args.getString("a_url", "")
        val authorName   = args.getString("u_name", "")
        val foodImageUrl = args.getString("f_url", "")
        val likes        = args.getInt("likes", 0)
        val liked        = args.getBoolean("liked", false)
        val ingredients  = args.getStringArrayList("ingredients") ?: arrayListOf()

        // Картинка статична — просто загружаем
        Glide.with(this).load(foodImageUrl).centerCrop().into(heroImage)

        backBtn.setOnClickListener { parentFragmentManager.popBackStack() }

        val items: MutableList<Item> = mutableListOf(
            InfoItem(name, description, cookingTime, avatarUrl, authorName, foodImageUrl, likes, liked),
            SectionTitleItem("Ingredients")
        )
        for (ing in ingredients) {
            items.add(IngredientItem(ing))
        }
        items.add(SectionTitleItem("Steps"))
        items.add(StepItem(1,
            "Mix all dry ingredients in a large bowl. Sift and combine thoroughly.",
            "https://i.pinimg.com/1200x/bd/a2/c9/bda2c98b829d1f3615ffacbe8ba30f58.jpg"))
        items.add(StepItem(2,
            "Add wet ingredients gradually while stirring. Do not over-mix the batter.",
            "https://i0.wp.com/thehomecookbible.com/wp-content/uploads/2026/03/Pour-wet-into-dry.webp?resize=1024%2C768&ssl=1"))
        items.add(StepItem(3,
            "Cook on medium heat until golden. Serve immediately with fresh toppings.",
            "https://i.pinimg.com/736x/d3/5e/2a/d35e2a9ca6b99934c40ea96c4a652644.jpg"))

        val adapter = RecipeDetailAdapter(items) {
            parentFragmentManager.popBackStack()
        }
        recyclerView.adapter = adapter
    }
}
