package com.example.projectkmept

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectkmept.screens.AntonchikActivity
import com.example.projectkmept.screens.ArsenchikActivity
import com.example.projectkmept.screens.AbdullinActivity
import com.example.projectkmept.screens.KovalevActivitry
import com.example.projectkmept.screens.RomaniucActivity
import com.example.projectkmept.screens.LyraActivity



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.antonchikButton).setOnClickListener {
            val intent = Intent(this, AntonchikActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.oorzhakButton).setOnClickListener {
            val intent = Intent(this, ArsenchikActivity::class.java)
            startActivity(intent)
        }
        
        findViewById<Button>(R.id.AbdullinButton).setOnClickListener {
            val intent = Intent(this, AbdullinActivity::class.java)
            startActivity(intent)
        }
        
        findViewById<Button>(R.id.RomaniucButton).setOnClickListener {
            val intent = Intent(this, RomaniucActivity::class.java)
            startActivity(intent)
        }
        
        findViewById<Button>(R.id.lyraButton).setOnClickListener {
            val intent = Intent(this, LyraActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.KovalevButton).setOnClickListener {
             val intent = Intent(this, KovalevActivitry::class.java)
             startActivity(intent)
        }
    }
}
