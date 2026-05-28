package com.example.projectkmept.network

import com.example.projectkmept.model.RecipesResponse
import retrofit2.Call
import retrofit2.http.GET

interface RecipeApiService {
    @GET("recipes")
    fun getRecipes(): Call<RecipesResponse>
}
