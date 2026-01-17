package com.example.projectkmept.romaniuc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R
import com.example.projectkmept.romaniuc.Romaniuc.UsersAdapter

class RomaniucActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_romaniuc) // Убедитесь, что это правильный layout

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Создаем тестовые данные как на скриншоте
        val users = listOf(
            UsersAdapter.User(
                name = "Dean Winchester",
                timeInfo = "now following you · 1h",
                avatarResId = R.drawable.avatar1, // Замените на ваши ресурсы
                isFollowing = false
            ),
            UsersAdapter.User(
                name = "John Steve",
                timeInfo = "now following you · 1h",
                avatarResId = R.drawable.avatar2,
                isFollowing = false
            ),
            UsersAdapter.User(
                name = "Sam Winchester",
                timeInfo = "now following you · 1h",
                avatarResId = R.drawable.avatar3,
                isFollowing = true
            ),
            UsersAdapter.User(
                name = "Dean Winchester",
                timeInfo = "now following you · 1h",
                avatarResId = R.drawable.avatar4,
                isFollowing = false
            ),
            // Добавьте остальные пользователи по аналогии
            UsersAdapter.User(
                name = "Алексей Иванов",
                timeInfo = "now following you · 2h",
                avatarResId = R.drawable.avatar5,
                isFollowing = false
            ),
            UsersAdapter.User(
                name = "Мария Петрова",
                timeInfo = "now following you · 3h",
                avatarResId = R.drawable.avatar6,
                isFollowing = true
            ),
            // ... остальные пользователи
        )

        // Настройка RecyclerView
        val adapter = UsersAdapter(users)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Если нужно добавить разделители:
        // val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        // recyclerView.addItemDecoration(divider)

        recyclerView.setHasFixedSize(true)
    }
}