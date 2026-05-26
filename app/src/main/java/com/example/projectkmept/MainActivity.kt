package com.example.projectkmept

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.projectkmept.screens.YuldoshevActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Сразу открываем экран логина (или можно добавить кнопку)
        startActivity(Intent(this, YuldoshevActivity::class.java))
    }
}