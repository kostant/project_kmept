package com.example.projectkmept.Abdullin228

data class UserCard(
    val userName: String,
    val avatarUrl: String,
    val foodImageUrl: String,
    val foodTitle: String,
    val cookingTime: String,
    var isFollowed: Boolean,
    var likes: Int = 0,
    val description: String = "Your recipe has been uploaded, you can see it on your profile.",
    val ingredients: List<String> = listOf("4 Eggs", "1/2 Butter", "1/2 Milk")
)
