package com.example.projectkmept.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R
import com.example.projectkmept.adapter.GridItemAdapter
import com.example.projectkmept.model.GridItem
import com.example.projectkmept.model.RecipesResponse
import com.example.projectkmept.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GridFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var statusText: TextView
    private lateinit var adapter: GridItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        statusText = view.findViewById(R.id.statusText)

        adapter = GridItemAdapter(emptyList()) { item ->
            item.recipe?.let { recipe ->
                (activity as? ArsenchikActivity)?.openRecipeDetail(recipe)
            }
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        loadRecipes()
    }

    private fun loadRecipes() {
        progressBar.visibility = View.VISIBLE
        statusText.visibility = View.VISIBLE
        statusText.text = getString(R.string.loading_recipes)

        RetrofitClient.apiService.getRecipes().enqueue(object : Callback<RecipesResponse> {
            override fun onResponse(call: Call<RecipesResponse>, response: Response<RecipesResponse>) {
                if (!isAdded) return
                progressBar.visibility = View.GONE

                val recipes = response.body()?.recipes.orEmpty()
                if (response.isSuccessful && recipes.isNotEmpty()) {
                    val gridItems = recipes.map { recipe ->
                        GridItem(
                            title = recipe.name,
                            subtitle = buildSubtitle(recipe),
                            imageUrl = recipe.image,
                            recipe = recipe
                        )
                    }
                    adapter.updateItems(gridItems)
                    statusText.visibility = View.GONE
                } else {
                    showError(getString(R.string.error_empty_response))
                }
            }

            override fun onFailure(call: Call<RecipesResponse>, t: Throwable) {
                if (!isAdded) return
                progressBar.visibility = View.GONE
                showError(getString(R.string.error_loading_recipes, t.localizedMessage ?: "Unknown error"))
            }
        })
    }

    private fun buildSubtitle(recipe: com.example.projectkmept.model.ApiRecipe): String {
        val totalTime = (recipe.prepTimeMinutes ?: 0) + (recipe.cookTimeMinutes ?: 0)
        val timePart = if (totalTime > 0) "$totalTime min" else getString(R.string.unknown_time)
        val difficultyPart = recipe.difficulty ?: getString(R.string.unknown_difficulty)
        return "$timePart • $difficultyPart"
    }

    private fun showError(message: String) {
        statusText.visibility = View.VISIBLE
        statusText.text = message
    }
}
