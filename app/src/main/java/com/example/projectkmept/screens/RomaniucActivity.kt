package com.example.projectkmept.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectkmept.R
import com.example.projectkmept.Romaniuc.Romaniuc_Adapter

class RomaniucActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_romaniuc)

        val recyclerView: RecyclerView = findViewById(R.id.myrecycler)
        val adapter =  Romaniuc_Adapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(this)
    }




}