package com.example.projectdeveloper.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdeveloper.R
import com.example.projectdeveloper.model.CustomAdapter
import com.example.projectdeveloper.model.Modele

class Projet_Delete : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomAdapter
    lateinit var back:Button
    companion object{
        var dataDelete:ArrayList<Modele> = ArrayList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        back = findViewById(R.id.back)
        recyclerView = findViewById(R.id.projetDelete)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(applicationContext,getDataDelete())
        recyclerView.adapter = adapter

        back.setOnClickListener{
            var intent = Intent(applicationContext,PageHome::class.java)
            startActivity(intent)
        }
    }
    private fun getDataDelete(): ArrayList<Modele> {
        val names = ArrayList<Modele>()
        for (modele in dataDelete){
            names.add(modele)
        }
        return names
    }
}