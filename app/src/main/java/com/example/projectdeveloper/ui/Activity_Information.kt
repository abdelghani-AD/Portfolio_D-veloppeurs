package com.example.projectdeveloper.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.projectdeveloper.R
class activity_Information : AppCompatActivity() {
    lateinit var retour:ImageView
    lateinit var image:ImageView
    lateinit var infoDeveloppeurs:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        retour = findViewById(R.id.retoure)
        image = findViewById(R.id.imgURL)
        infoDeveloppeurs = findViewById(R.id.informationDveloppeur)
    }

    override fun onStart() {
        super.onStart()
        retour.setOnClickListener{
            var intent = Intent(this,PageHome::class.java)
            startActivity(intent)
        }
// Récupérez les données de l'intent
        var langage = intent.getStringExtra("langage")
        var developpeur = intent.getStringExtra("developper")
        var imageRessource = intent.getIntExtra("image",0)

// Affichez les données dans les TextViews et l'ImageView
        image.setImageResource(imageRessource)
        var infoDeveloppeur ="Cette Application Développer par : Développeur :  $developpeur  En Langage : $langage ."
        var infoDeApp ="\n Cette application est conçue pour fournir une expérience utilisateur optimale en offrant des " +
                "fonctionnalités avancées et une interface conviviale. Elle vise à répondre aux besoins actuels du " +
                "marché et à offrir une solution innovante pour ses utilisateurs.\n"

        infoDeveloppeurs.text = infoDeveloppeur + infoDeApp

    }
}