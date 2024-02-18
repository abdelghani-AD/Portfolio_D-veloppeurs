package com.example.projectdeveloper.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdeveloper.R
import com.example.projectdeveloper.model.CustomAdapter
import com.example.projectdeveloper.model.Modele

class ProjetKotlin : AppCompatActivity() {
    lateinit var retour:ImageView
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projet_kotlin)

        retour = findViewById(R.id.retoureHome)
    }

    override fun onStart() {
        super.onStart()
        retour.setOnClickListener{
            var intent = Intent(this,PageHome::class.java)
            startActivity(intent)
        }
        var projets = intent.getParcelableArrayListExtra<Modele>("langageKotlin")

        if (projets != null){
            recyclerView = findViewById(R.id.recyclerViewKotlin)
            recyclerView.layoutManager = LinearLayoutManager(this)

            val customAdapter = CustomAdapter(this, projets)
            recyclerView.adapter = customAdapter
        }
        else{
            Toast.makeText(this,"Data n'est pas ajouter ", Toast.LENGTH_SHORT).show()
        }
    }
}