package com.example.projectdeveloper.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.projectdeveloper.R
import com.example.projectdeveloper.model.CustomAdapter
import com.example.projectdeveloper.model.Modele
import com.example.projectdeveloper.model.OnItemClickListener
import com.example.projectdeveloper.mvvm.ViewModele

class PageHome : AppCompatActivity() , OnItemClickListener {
    lateinit var vieModel: ViewModele

    companion object{
        var dataList:ArrayList<Modele> = ArrayList()
        lateinit var customAdapter:CustomAdapter
    }


    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_home)
        //Initialisé  RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Initialisé  Custome Adapter
        customAdapter = CustomAdapter(this, dataList,this)

        //Creation de Menu " Initialisé  Toolbar "
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

         vieModel = ViewModelProvider(this).get(ViewModele::class.java)
         Toast.makeText(this,vieModel.getModel().developper,Toast.LENGTH_SHORT).show()
    }
    override fun onStart() {
        super.onStart()
        //Ajouter data dans une liste
        dataList.add(Modele("Langage_Java", "Yassin El Fadili", R.drawable.img_java))
        dataList.add(Modele("Langage_Swift", "Imanouile Torran", R.drawable.img_swift))
        dataList.add(Modele("Langage_Java", "Aymen Abou Mohammed", R.drawable.img_java))
        dataList.add(Modele("Langage_Kotlin", "Nouri Mohammed Yassin", R.drawable.img_kotlin))
        dataList.add(Modele("Langage_Swift", "Ali Belkhair", R.drawable.img_swift))
        dataList.add(Modele("Langage_Java", "Tarik Fath-Allah", R.drawable.img_java))
        dataList.add(Modele("Langage_Kotlin", "Mohammed Errabii", R.drawable.img_kotlin))
        dataList.add(Modele("Langage_Swift", "Ayoub Douirek", R.drawable.img_swift))
        dataList.add(Modele("Langage_Java", "Ahmed El Ouazzani", R.drawable.img_java))
        dataList.add(Modele("Langage_Kotlin", "Mohammed El Hassani", R.drawable.img_kotlin))
        dataList.add(Modele("Langage_Swift", "Abdelghani Douirek", R.drawable.img_swift))
        dataList.add(Modele("Langage_Java", "Iman Baraka", R.drawable.img_java))
        dataList.add(Modele("Langage_Kotlin", "Zayed Tijanni", R.drawable.img_kotlin))
        dataList.add(Modele("Langage_Swift", "Sara Khalid", R.drawable.img_swift))
        //affecter Custome Adapter dans une RecyclerView
        recyclerView.adapter = customAdapter

    }
    // fonction filter les Projets By Langages qui developper cette Projet
    fun filterDataByLanguage(language: String): List<Modele> {
        return dataList.filter { it.langage == language }
    }
    // Inflat Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuhome, menu)
        return true
    }
    //les operations au momment de click dans chaque icon de menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            var itemId:Int = item.itemId
            if (itemId == R.id.iconHome){
                var intent = Intent(this,PageHome::class.java)
                startActivity(intent)
            }
            else if (itemId == R.id.iconAccount){
                supportFragmentManager.beginTransaction().replace(R.id.fragment, FragmentProfile())
                    .commit()
            }
            else if (itemId == R.id.iconLogout){
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        else if (itemId == R.id.ajouter){
            //supportFragmentManager.beginTransaction().replace(R.id.fragment, FragmentAddProjet()).commit()
            var intent = Intent(this,Ajouter::class.java)
            startActivity(intent)
        }
        else if (itemId == R.id.iconChat){
            var intent = Intent(this, ActivityChat::class.java)
            startActivity(intent)
        }
        else if (itemId == R.id.java){
            val filteredList = filterDataByLanguage("Langage_Java")
            var intent = Intent(this,ProjetJava::class.java)
            intent.putParcelableArrayListExtra("langageJava", ArrayList(filteredList))
            startActivity(intent)
        }
        else if (itemId == R.id.kotlin){
            val filteredList = filterDataByLanguage("Langage_Kotlin")
            var intent = Intent(this,ProjetKotlin::class.java)
            intent.putParcelableArrayListExtra("langageKotlin", ArrayList(filteredList))
            startActivity(intent)
        }
        else if (itemId == R.id.swift){
            val filteredList = filterDataByLanguage("Langage_Swift")
            var intent = Intent(this,ProjetSwift::class.java)
            intent.putParcelableArrayListExtra("langageSwift", ArrayList(filteredList))
            startActivity(intent)
        }
        //Intent Implicite Youtub
        else if (itemId == R.id.Youtub){
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.youtube.com")
                startActivity(intent)
        }
        //Intent Implicite GitHub
        else if (itemId == R.id.GitHub){
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://github.com/abdelghani-AD")
                startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onItemClick(modele: Modele) {
        //Au momment de clic en Button More dans RecyclerView
        //Ajouter les informations en  activity Information
        var intent = Intent(this,activity_Information::class.java)

        intent.putExtra("langage",modele.langage)
        intent.putExtra("developper",modele.developper)
        intent.putExtra("image",modele.image)

        startActivity(intent)
    }
}
