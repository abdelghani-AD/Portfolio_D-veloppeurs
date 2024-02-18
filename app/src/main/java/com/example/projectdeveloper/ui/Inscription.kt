package com.example.projectdeveloper.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectdeveloper.R
import com.example.projectdeveloper.mvvm.ViewModele

class Inscription : AppCompatActivity() {
    lateinit var editName: EditText
     lateinit  var editPrenom:EditText
    lateinit  var editPhone:EditText
    lateinit  var editMail:EditText
    lateinit  var editPassword:EditText
    lateinit var checkBox: CheckBox
    lateinit var submit: Button
    lateinit var cancel:Button
    lateinit var signUpMVVM:ViewModele

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)
        editName = findViewById(R.id.editName)
        editPrenom = findViewById(R.id.editPrenom)
        editPhone = findViewById(R.id.editPhone)
        editMail = findViewById(R.id.editMail)
        editPassword = findViewById(R.id.editPassword)
        checkBox = findViewById(R.id.checkbox)
        cancel = findViewById(R.id.cancel)
        submit = findViewById(R.id.inscrire)

        signUpMVVM = ViewModelProvider(this).get(ViewModele::class.java)

        signUpMVVM.isDataSucces.observe(this , Observer { isSucces ->
            if (isSucces){
                val intent = Intent(this, PageHome::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this,"Champs invalide ",Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onStart() {
        super.onStart()
        submit.setOnClickListener{
            var name = editName.text.toString()
            var prenom = editPrenom.text.toString()
            var phone = editPhone.text.toString()
            var email = editMail.text.toString()
            var password = editPassword.text.toString()
            var checkBox = checkBox.isChecked

            signUpMVVM.validCampsSignUp(name,prenom,phone,email,password,checkBox)
        }
        cancel.setOnClickListener{
            editName.text.clear()
            editPrenom.text.clear()
            editPhone.text.clear()
            editMail.text.clear()
            editPassword.text.clear()
            checkBox.isChecked = false
        }
    }

   /* override fun onStart() {
        submit.setOnClickListener{
            //fonction de validation des champs
            valideChamps()
        }
        cancel.setOnClickListener{
            editName.text.clear()
            editPrenom.text.clear()
            editPhone.text.clear()
            editMail.text.clear()
            editPassword.text.clear()
            checkBox.isChecked = false
        }
        Toast.makeText(this,"tout les champs et null",Toast.LENGTH_SHORT).show()
        super.onStart()
    }
    //fonction de validation des champs
    public fun valideChamps() {
        val name = editName!!.text.toString()
        val prenom = editPrenom.text.toString()
        val phone = editPhone.text.toString()
        val mail = editMail.text.toString()
        val password = editPassword.text.toString()
        if (name.isEmpty() || prenom.isEmpty() || phone.isEmpty()
            || mail.isEmpty() || password.isEmpty() || !checkBox.isChecked
        ) {
            Toast.makeText(this, "Champs invalide ", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, PageHome::class.java)
            startActivity(intent)

        }
    }*/
}