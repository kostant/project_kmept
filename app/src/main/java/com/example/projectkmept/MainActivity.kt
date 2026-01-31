package com.example.projectkmept

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager // Обязательно импортируй это
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.romaniuc.Romaniuc.UsersAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_romaniuc)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // 1. Создаем данные под новый формат (автор, блюдо, категория, время, аватар, еда)
        val foodList = listOf(
            UsersAdapter.User(
                authorName = "Calum Lewis",
                foodTitle = "Pancake",
                category = "Food",
                cookingTime = ">60 mins",
                avatarUrl = "https://i.pinimg.com/1200x/1e/8c/1b/1e8c1b22db390ac5c59782692b777d40.jpg",
                foodImageUrl = "https://www.liway.ru/upload/medialibrary/036/0367b3565bda6a8a0dac3fabc48e9835.jpg" // Твоя картинка блинов
            ),
            UsersAdapter.User(
                authorName = "Eilif Sonas",
                foodTitle = "Salad",
                category = "Food",
                cookingTime = ">60 mins",
                avatarUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRya24a3K3d4sudzJelCcp7AHB3UANTpPBGQg&s",
                foodImageUrl = "https://www.bahroma1.ru/goods/img-articles/kak-pravilno-fotografirovat.jpg" // Твоя картинка салата
            ),
            UsersAdapter.User(
                authorName = "Elena Shelby",
                foodTitle = "Pancake",
                category = "Food",
                cookingTime = ">60 mins",
                avatarUrl = "https://sun1-95.userapi.com/impg/-kJZBISahLLGK0KtZ1udOkOnmrwsmsXzn8m17w/UixIKsfwVkY.jpg?size=520x0&quality=95&sign=cf3f88e05fcd7ddf1ec82bfae46d0a57",
                foodImageUrl = "https://cafebrynza.ru/images/articles/5-poleznykh-svojstv-goryachej-edy_66a272bd082bc2.png"
            ),
            UsersAdapter.User(
                authorName = "John Priyadi",
                foodTitle = "Salad",
                category = "Food",
                cookingTime = ">60 mins",
                avatarUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD2QcBlCrplSFxOw6t_S5hiCutqX-hhVRE1A&s",
                foodImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVOmgjq7TsKh5LAoRZQMTvNSrIuBeqTDXanA&s"
            )
        )

        // 2. Инициализируем адаптер
        val adapter = UsersAdapter(foodList)
        recyclerView.adapter = adapter

        // 3. САМОЕ ВАЖНОЕ: Устанавливаем GridLayoutManager (сетка в 2 колонки)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        recyclerView.setHasFixedSize(true)
    }
}
