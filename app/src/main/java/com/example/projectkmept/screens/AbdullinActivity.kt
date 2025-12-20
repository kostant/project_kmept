package com.example.projectkmept.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R
import com.example.projectkmept.abdullin.abdullinAdapter

class AbdullinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abdullin)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = abdullinAdapter()
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager( this)
    }
}