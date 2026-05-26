package com.example.projectkmept

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.projectkmept.screens.ArsenchikActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openButton: Button = findViewById(R.id.openFragmentsButton)
        openButton.setOnClickListener {
            startActivity(Intent(this, ArsenchikActivity::class.java))
        }
    }
}
