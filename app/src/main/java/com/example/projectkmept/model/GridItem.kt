package com.example.projectkmept.model

data class GridItem(
    val title: String,
    val subtitle: String,
    val imageUrl: String = "",
    val recipe: ApiRecipe? = null
)
