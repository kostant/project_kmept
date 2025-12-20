package com.example.projectkmept.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R
import com.example.projectkmept.oorzhak.ArsenchikAdapter

class ArsenchikActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oorzhak)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = ArsenchikAdapter()
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}