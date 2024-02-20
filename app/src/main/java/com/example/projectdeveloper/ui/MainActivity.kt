package com.example.projectdeveloper.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.projectdeveloper.R
import com.example.projectdeveloper.model.Modele
import com.example.projectdeveloper.model.MyWork
import com.example.projectdeveloper.model.OnItemClickListener
import com.example.projectdeveloper.mvvm.ViewModele


class MainActivity : AppCompatActivity() {
    lateinit var editUser: EditText
    lateinit var editPassword: EditText
    lateinit var login: Button
    lateinit var signUp: Button
    lateinit var loginMVVM : ViewModele
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editUser = findViewById(R.id.editUser)
        editPassword = findViewById(R.id.editPassword)
        login = findViewById(R.id.submit)
        signUp = findViewById(R.id.noveauCompte)

        loginMVVM = ViewModelProvider(this).get(ViewModele::class.java)

        loginMVVM.isDataSucces.observe(this , Observer { isSucces ->
            if (isSucces){

                //transfert à la page d'inscription
                val intent = Intent(this, PageHome::class.java)
                startActivity(intent)
                finish()
            }
            else{
                var myToast :Toast = Toast.makeText(this, "Champs Invalide ", Toast.LENGTH_SHORT)
                myToast.show()
            }
        })

    }
    override fun onStart() {
        super.onStart()

        var constraints:Constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        var workRequest:WorkRequest = OneTimeWorkRequestBuilder<MyWork>()
            .setConstraints(constraints)
            .build()

        login.setOnClickListener{
            var user : String = editUser.text.toString()
            var password:String = editPassword.text.toString()
            loginMVVM.valideChampsLogin(user , password)

        }
        signUp.setOnClickListener{
            var intent = Intent(this , Inscription::class.java)
            startActivity(intent)
            WorkManager.getInstance(applicationContext).enqueue(workRequest)
        }
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        WorkManager.getInstance(applicationContext)
            .getWorkInfoByIdLiveData(workRequest.id)
            .observe(this, Observer { workInfo ->
                var resultat:TextView = findViewById(R.id.resultat)
                resultat.text ="Resultat : ${workInfo.state.isFinished}"
            })
    }
}
