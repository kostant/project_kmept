package com.example.projectkmept.model

import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("recipes") val recipes: List<ApiRecipe>
)

data class ApiRecipe(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("cookTimeMinutes") val cookTimeMinutes: Int? = null,
    @SerializedName("prepTimeMinutes") val prepTimeMinutes: Int? = null,
    @SerializedName("difficulty") val difficulty: String? = null,
    @SerializedName("cuisine") val cuisine: String? = null,
    @SerializedName("ingredients") val ingredients: List<String> = emptyList(),
    @SerializedName("instructions") val instructions: List<String> = emptyList(),
    @SerializedName("rating") val rating: Double? = null,
    @SerializedName("reviewCount") val reviewCount: Int? = null,
    @SerializedName("mealType") val mealType: List<String> = emptyList(),
    @SerializedName("tags") val tags: List<String> = emptyList(),
)
