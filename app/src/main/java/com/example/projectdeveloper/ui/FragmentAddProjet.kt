package com.example.projectdeveloper.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.Activity
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projectdeveloper.R
import com.example.projectdeveloper.model.Modele
import com.example.projectdeveloper.mvvm.ViewModele

class FragmentAddProjet : Fragment() {
    lateinit var image:ImageView
    lateinit var nameLangage:EditText
    lateinit var nameDevelopper:EditText
    lateinit var addProjet:Button
    private val  REQUEST_PERMISSION_CODE = 123
    private var selectedImgURL: Uri? = null
    lateinit var vieModel:ViewModele
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_add_projet, container, false)

        vieModel = ViewModelProvider(this).get(ViewModele::class.java)

        image = view.findViewById(R.id.imgLangage)
        nameLangage = view.findViewById(R.id.nLangage)
        nameDevelopper = view.findViewById(R.id.nameDevelopper)
        addProjet = view.findViewById(R.id.ajouterProjet)
        image.setOnClickListener{
            if (checkGalleryPermission()){
                openGallery()
            }
            else{
                requestGalleryPermission()
            }
        }
        addProjet.setOnClickListener {
            validateFields()
        }
        return view
    }
    private fun checkGalleryPermission(): Boolean {
        // Vérifier la permission pour lire le stockage externe
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
    }
    private fun requestGalleryPermission() {
        // Demander la permission d'accéder à la galerie
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_PERMISSION_CODE)
    }
    private fun openGallery() {
        // Code pour ouvrir la galerie ici
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_PERMISSION_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PERMISSION_CODE &&
            resultCode == Activity.RESULT_OK &&
            data != null){
            // L'utilisateur a sélectionné une image
            selectedImgURL = data.data
            image.setImageURI(selectedImgURL)
        }
    }
    private fun validateFields(){
        // Validate the fields here
        val langageName = nameLangage.text.toString().trim()
        val developerName = nameDevelopper.text.toString().trim()
        if (!langageName.isEmpty() && !developerName.isEmpty() && selectedImgURL != null ) {
            //Toast.makeText(context, "Champs est validé", Toast.LENGTH_SHORT).show()
           // PageHome.dataList.add(Modele(langageName,developerName,selectedImgURL as Int))

            vieModel.setModel(Modele(langageName,developerName,selectedImgURL as Int))

        }else{
            Toast.makeText(context, "Champs non  validé", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentAddProjet().apply {
                arguments = Bundle().apply {

                }
            }
    }
}