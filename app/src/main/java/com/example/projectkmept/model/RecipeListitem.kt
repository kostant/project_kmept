package com.example.projectkmept.model

open class RecipeListItem

class InfoItem(
    val name: String,
    val description: String,
    val time: String,
    val avatarUrl: String = "",
    val authorName: String = "Elena Shelby",
    var likes: Int = 273,
    var isLiked: Boolean = false,
    val heroImageUrl: String = "https://avatars.mds.yandex.net/i?id=f7cc9205c2fb8acaa1db0b4645afbca4_l-4076784-images-thumbs&n=13"   // URL главной картинки (если пусто — drawable)
) : RecipeListItem()

class SectionTitleItem(val title: String) : RecipeListItem()

class IngredientItem(val name: String) : RecipeListItem()

class StepItem(
    val number: Int,
    val instruction: String,
    val image: String
) : RecipeListItem()
