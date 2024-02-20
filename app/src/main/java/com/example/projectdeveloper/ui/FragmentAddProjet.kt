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
import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.projectdeveloper.R
import com.example.projectdeveloper.model.Modele
import com.example.projectdeveloper.mvvm.ViewModele

class FragmentAddProjet : Fragment() {
    lateinit var image: ImageView
    lateinit var nameLangage: Spinner
    lateinit var nameDevelopper: EditText
    lateinit var addProjet: Button
    private val  REQUEST_PERMISSION_CODE = 123
    private var selectedImgURL: Uri? = null
    lateinit var vieModel:ViewModele
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_add_projet, container, false)

        vieModel = ViewModelProvider(this).get(ViewModele::class.java)

        image = view.findViewById(R.id.imgViewLangage)
        nameLangage = view.findViewById(R.id.spinnerLangage)
        nameDevelopper = view.findViewById(R.id.editNameDevelopper)
        addProjet = view.findViewById(R.id.btnAjouterProjet)

        var langages:ArrayList<String> = ArrayList()
        langages.add("Langage_Java")
        langages.add("Langage_Kotlin")
        langages.add("Langage_Swift")
        var spinnerItem:ArrayAdapter<String> = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,langages)
        nameLangage.adapter = spinnerItem

        image.setOnClickListener{
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
                // Code pour ouvrir la galerie ici
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_PERMISSION_CODE)
            }
            else{ // Demander la permission d'accéder à la galerie
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION_CODE)
            }
        }
        addProjet.setOnClickListener {
            validateFields()
        }
        return view
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
        val langageName = nameLangage.selectedItem.toString()
        val developerName = nameDevelopper.text.toString()
        if (!langageName.isEmpty() && !developerName.isEmpty() && selectedImgURL != null ) {
            //Toast.makeText(context, "Champs est validé", Toast.LENGTH_SHORT).show()
           // PageHome.dataList.add(Modele(langageName,developerName,selectedImgURL as Int))

            vieModel.setModel(Modele(langageName,developerName,selectedImgURL as Int))

            var intent : Intent = Intent(requireContext(),PageHome::class.java)
            startActivity(intent)

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