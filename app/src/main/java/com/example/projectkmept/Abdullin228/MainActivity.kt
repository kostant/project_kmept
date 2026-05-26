package com.example.projectkmept.Abdullin228

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projectkmept.R

class MainActivity : AppCompatActivity() {

    private lateinit var tab1: TextView
    private lateinit var tab2: TextView
    private lateinit var tab3: TextView
    private lateinit var tab4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main228)

        tab1 = findViewById(R.id.tab1)
        tab2 = findViewById(R.id.tab2)
        tab3 = findViewById(R.id.tab3)
        tab4 = findViewById(R.id.tab4)

        // Показываем GridFragment при старте
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GridFragment())
                .commit()
            highlightTab(tab1)
        }

        tab1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GridFragment())
                .commit()
            highlightTab(tab1)
        }

        tab2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RedFragment())
                .commit()
            highlightTab(tab2)
        }

        tab3.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BlueFragment())
                .commit()
            highlightTab(tab3)
        }

        tab4.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GreenFragment())
                .commit()
            highlightTab(tab4)
        }
    }

    private fun highlightTab(selected: TextView) {
        val tabs = listOf(tab1, tab2, tab3, tab4)
        tabs.forEach { it.setTextColor(ContextCompat.getColor(this, R.color.tabInactive)) }
        selected.setTextColor(ContextCompat.getColor(this, R.color.tabActive))
    }
}
