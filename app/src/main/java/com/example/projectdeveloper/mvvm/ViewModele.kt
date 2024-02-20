package com.example.projectdeveloper.mvvm

import android.graphics.ColorSpace.Model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectdeveloper.model.Modele
import com.example.projectdeveloper.repository.Repository

class ViewModele : ViewModel(){

    var repository = Repository()

    lateinit var modele:Modele
    fun setModel(modele: Modele){
        this.modele= modele
    }
    fun getModel():Modele{
        return Modele("java","Douirek",2)
    }

    val isDataSucces = MutableLiveData<Boolean>()

    fun valideChampsLogin(user: String, password: String) {
        isDataSucces.value = repository.login(user, password)
    }


    fun validCampsSignUp(name: String, prenme: String, phone: String, mail: String, password: String, isChecked: Boolean) {
        isDataSucces.value = repository.signUp(name, prenme, phone, mail, password, isChecked)
    }

   /* val isSignUpSucces = MutableLiveData<Boolean>()
    fun valideChampsLogin(user:String , password:String){
        if (user.isEmpty() || password.isEmpty()){
            isLoginSucces.value = false
        }else{
            isLoginSucces.value = true
        }
    }
    fun validCampsSignUp(name:String,prenme:String,phone:String,mail:String,password: String,isChecked:Boolean){
        if (name.isEmpty() || prenme.isEmpty() || phone.isEmpty() || mail.isEmpty() || password.isEmpty() || !isChecked){
            isSignUpSucces.value = false
        }
        else{
            isSignUpSucces.value = true
        }
    }*/
}