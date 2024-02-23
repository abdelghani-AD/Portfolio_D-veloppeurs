package com.example.projectdeveloper.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdeveloper.R
import com.example.projectdeveloper.model.CustomAdapter
import com.example.projectdeveloper.model.Modele
import com.example.projectdeveloper.model.OnItemClickListener

class PageHome : AppCompatActivity() , OnItemClickListener {
    companion object{
        lateinit var customAdapter:CustomAdapter
        var dataList:ArrayList<Modele> = ArrayList()
    }
    lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
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
        dataList.add(Modele("Langage_Kotlin", "Ahmed El Ouazzani", R.drawable.img_kotlin))

        //affecter Custome Adapter dans une RecyclerView
        recyclerView.adapter = customAdapter

//On Swipe To Right
        var retreat = findViewById<Button>(R.id.retreat)
        var deletedItem: Modele? = null
        var deleteItemPosition = -1
        var itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                deletedItem = dataList[position]
                dataList.removeAt(position)
                customAdapter.notifyItemRemoved(position)

                Projet_Delete.dataDelete.add(deletedItem!!)

                retreat.visibility = View.VISIBLE

                deleteItemPosition = position
                Handler(Looper.getMainLooper()).postDelayed({
                    if (deletedItem != null){
                        retreat.visibility = View.GONE
                        deletedItem = null
                        deleteItemPosition = -1
                    }
                },3000)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
        retreat.setOnClickListener{
           deletedItem?.let {
               dataList.add(deleteItemPosition , it)
               customAdapter.notifyItemInserted(deleteItemPosition)
               retreat.visibility = View.GONE
               deletedItem = null
               deleteItemPosition = -1
           }
        }
    }

    // fonction filter les Projets by Langages qui developper cette Projet
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
            when(item.itemId){
                R.id.iconHome -> { val intent = Intent(this, PageHome::class.java)
                    startActivity(intent) }
                R.id.iconLogout -> { val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent) }
                R.id.ajouter -> { supportFragmentManager.beginTransaction().replace(R.id.fragment,FragmentAddProjet())
                    .commit()}
                R.id.iconDelete -> { val intent = Intent(this, Projet_Delete::class.java)
                    startActivity(intent)}
                R.id.iconChat -> { val intent = Intent(this, ActivityChat::class.java)
                    startActivity(intent) }

                R.id.java -> { val filteredList = filterDataByLanguage("Langage_Java")
                    val intent = Intent(this, ProjetJava::class.java)
                    intent.putParcelableArrayListExtra("langageJava", ArrayList(filteredList))
                    startActivity(intent) }

                R.id.kotlin -> { val filteredList = filterDataByLanguage("Langage_Kotlin")
                    val intent = Intent(this, ProjetKotlin::class.java)
                    intent.putParcelableArrayListExtra("langageKotlin", ArrayList(filteredList))
                    startActivity(intent) }

                R.id.swift -> { val filteredList = filterDataByLanguage("Langage_Swift")
                    val intent = Intent(this, ProjetSwift::class.java)
                    intent.putParcelableArrayListExtra("langageSwift", ArrayList(filteredList))
                    startActivity(intent) }

                R.id.Youtub -> { val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.youtube.com")
                    startActivity(intent) }
                R.id.GitHub -> { val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://github.com/abdelghani-AD")
                    startActivity(intent) }
                R.id.darkMode -> { darkMode() }
            }
        return super.onOptionsItemSelected(item)
    }
    override fun onItemClick(modele: Modele) {
        //Au momment de clic en Button More dans RecyclerView
        //Ajouter les informations en  activity_Information
        var intent = Intent(this,activity_Information::class.java)

        intent.putExtra("langage",modele.langage)
        intent.putExtra("developper",modele.developper)
        intent.putExtra("image",modele.image)

        startActivity(intent)
    }
    private fun darkMode() {
        //Récupérer le mode dark qui utilisé dans l'application
        var appCompatDelegate = AppCompatDelegate.getDefaultNightMode()
        var newNightMode =
            if (appCompatDelegate == AppCompatDelegate.MODE_NIGHT_NO) {
                //Le mode dark est activé.
            AppCompatDelegate.MODE_NIGHT_YES
        }
        else {
            // Le mode dark est désactivé
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(newNightMode)
    }
}
