package com.example.projectdeveloper.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projectdeveloper.R


class FragmentProfile : Fragment() {
    //declarer les variables
    lateinit var editName:TextView
    lateinit var editPrenom:TextView
    lateinit var editNumberPhone:TextView
    lateinit var editEmail:TextView
    lateinit var editPassword:TextView
    lateinit var update:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate  layout dans  fragment
        var view =inflater.inflate(R.layout.fragment_profile, container, false)
        //affecter les id dans les variables
        editName = view.findViewById(R.id.fName)
        editPrenom = view.findViewById(R.id.fPrenom)
        editNumberPhone = view.findViewById(R.id.fPhone)
        editEmail = view.findViewById(R.id.fEmail)
        editPassword = view.findViewById(R.id.fPassword)
        update = view.findViewById(R.id.modifier)

        update.setOnClickListener{
            editName.text = " "
            editPrenom.text = " "
            editNumberPhone.text = " "
            editEmail.text = " "
            editPassword.text = " "

            //Intent vers page Inscription
        }


       var github:TextView = view.findViewById(R.id.github)
        var youtub:TextView = view.findViewById(R.id.youtub)
//Intent implicite
        github.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/abdelghani-AD")
            startActivity(intent)
        }
        youtub.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/channel/UCvmJk3xFllD0BU1Y4W8bGhA")
            startActivity(intent)
        }
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    companion object {

        fun newInstance() = FragmentProfile().apply {
                arguments = Bundle().apply {

                }
        }
    }
}