package com.example.projectkmept.screens

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
import com.example.projectkmept.adapter.RecipeDetailAdapter
import com.example.projectkmept.model.ApiRecipe
import com.example.projectkmept.model.IngredientItem
import com.example.projectkmept.model.InfoItem
import com.example.projectkmept.model.RecipeListItem
import com.example.projectkmept.model.SectionTitleItem
import com.example.projectkmept.model.StepItem
import com.google.gson.Gson

class RecipeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRecipe = arguments?.getString(ARG_RECIPE_JSON)?.let {
            Gson().fromJson(it, ApiRecipe::class.java)
        }

        val items = if (apiRecipe != null) buildRecipeItems(apiRecipe) else buildFallbackRecipeItems()

        val heroImage: ImageView = view.findViewById(R.id.heroImage)
        val infoItem = items.filterIsInstance<InfoItem>().firstOrNull()
        if (infoItem != null && infoItem.heroImageUrl.isNotEmpty()) {
            Glide.with(this)
                .load(infoItem.heroImageUrl)
                .centerCrop()
                .placeholder(R.drawable.bg_recipe_hero)
                .into(heroImage)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecipeDetailAdapter(items)
    }

    private fun buildRecipeItems(recipe: ApiRecipe): List<RecipeListItem> {
        val totalTime = (recipe.prepTimeMinutes ?: 0) + (recipe.cookTimeMinutes ?: 0)
        val detailLine = buildString {
            append(recipe.cuisine ?: getString(R.string.recipe_default_category))
            append(" • ")
            append(if (totalTime > 0) "$totalTime min" else getString(R.string.unknown_time))
            recipe.difficulty?.takeIf { it.isNotBlank() }?.let {
                append(" • ")
                append(it)
            }
        }

        val description = buildString {
            recipe.tags.takeIf { it.isNotEmpty() }?.let {
                append(it.joinToString(prefix = "Tags: "))
                append("\n\n")
            }
            recipe.mealType.takeIf { it.isNotEmpty() }?.let {
                append(it.joinToString(prefix = "Meal type: "))
                append("\n\n")
            }
            append(getString(R.string.recipe_rating_template, recipe.rating ?: 0.0, recipe.reviewCount ?: 0))
        }

        val items = mutableListOf<RecipeListItem>()
        items += InfoItem(
            name = recipe.name,
            description = description,
            time = detailLine,
            authorName = getString(R.string.retrofit_author),
            likes = (recipe.reviewCount ?: 0).coerceAtLeast(1),
            heroImageUrl = recipe.image
        )
        items += SectionTitleItem(getString(R.string.ingredients_title))
        items += recipe.ingredients.map { IngredientItem(it) }
        items += SectionTitleItem(getString(R.string.steps_title))
        items += recipe.instructions.mapIndexed { index, instruction ->
            StepItem(index + 1, instruction, recipe.image)
        }
        return items
    }

    private fun buildFallbackRecipeItems(): List<RecipeListItem> {
        val fallback = ApiRecipe(
            id = 0,
            name = "Cacao Maca Walnut Milk",
            image = "https://images.unsplash.com/photo-1547592180-85f173990554?q=80&w=1200&auto=format&fit=crop",
            prepTimeMinutes = 5,
            cookTimeMinutes = 5,
            difficulty = "Easy",
            cuisine = "Drink",
            ingredients = listOf(
                "2 cups almond milk",
                "2 tbsp cacao powder",
                "1 tsp maca powder",
                "1 handful walnuts",
                "1 banana",
                "1 tsp honey"
            ),
            instructions = listOf(
                "Add almond milk, banana, cacao and maca powder into a blender bowl.",
                "Blend until the texture becomes smooth and creamy without lumps.",
                "Pour into a glass, top with chopped walnuts and serve chilled."
            ),
            rating = 4.8,
            reviewCount = 273,
            mealType = listOf("Breakfast"),
            tags = listOf("healthy", "drink", "quick")
        )
        return buildRecipeItems(fallback)
    }

    companion object {
        private const val ARG_RECIPE_JSON = "ARG_RECIPE_JSON"

        fun newInstance(recipe: ApiRecipe): RecipeFragment {
            return RecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_RECIPE_JSON, Gson().toJson(recipe))
                }
            }
        }
    }
}
