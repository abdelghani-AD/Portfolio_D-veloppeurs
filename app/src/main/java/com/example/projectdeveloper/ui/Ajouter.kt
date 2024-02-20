package com.example.projectdeveloper.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.projectdeveloper.R

class Ajouter : AppCompatActivity() {
    lateinit var image: ImageView
    lateinit var nameLangage: Spinner
    lateinit var nameDevelopper: EditText
    lateinit var addProjet: Button
    private var  REQUEST_PERMISSION_CODE = 1
    lateinit var selectedImgURL: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter)
//        image = findViewById(R.id.imgViewLangage)
//        nameLangage = findViewById(R.id.spinnerLangage)
//        nameDevelopper = findViewById(R.id.editNameDevelopper)
//        addProjet = findViewById(R.id.btnAjouterProjet)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        var langages:ArrayList<String> = ArrayList()
//        langages.add("Langage_Java")
//        langages.add("Langage_Kotlin")
//        langages.add("Langage_Swift")
//        var spinnerItem:ArrayAdapter<String> = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,langages)
//        nameLangage.adapter = spinnerItem
//
//        image.setOnClickListener{
//            if (checkGalleryPermission()){
//                openGallery()
//            }
//            else{
//                requestGalleryPermission()
//            }
//        }
//        addProjet.setOnClickListener {
//            validateFields()
//        }
    }
//    private fun checkGalleryPermission(): Boolean {
//        // Vérifier la permission pour lire le stockage externe
//        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//    }
//    private fun requestGalleryPermission() {
//        // Demander la permission d'accéder à la galerie
//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_PERMISSION_CODE)
//    }
//    private fun openGallery() {
//        // Code pour ouvrir la galerie ici
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        startActivityForResult(intent, REQUEST_PERMISSION_CODE)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_PERMISSION_CODE &&
//            resultCode == Activity.RESULT_OK &&
//            data != null){
//            // L'utilisateur a sélectionné une image
//            selectedImgURL = data.data!!
//            image.setImageURI(selectedImgURL)
//        }
//        else{
//            Toast.makeText(applicationContext,"Image non sélectionnée",Toast.LENGTH_SHORT).show()
//        }
//    }
//    private fun validateFields(){
//        // Validate the fields here
//        val langageName = nameLangage.selectedItem.toString()
//        val developerName = nameDevelopper.text.toString()
//
//        if (!langageName.isEmpty() && !developerName.isEmpty() && selectedImgURL != null) {
//
//            var intent : Intent = Intent(applicationContext,PageHome::class.java)
//            startActivity(intent)
//        }else{
//            Toast.makeText(applicationContext, "Champs non  validé", Toast.LENGTH_SHORT).show()
//        }
//    }
}