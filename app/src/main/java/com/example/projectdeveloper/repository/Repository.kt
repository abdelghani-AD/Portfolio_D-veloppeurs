package com.example.projectdeveloper.repository

class Repository {
    //validation des champs de Login
    fun login(user: String, password: String): Boolean {
        if (!user.isEmpty() && !password.isEmpty()){
            return true
        }
        return false
    }
    //validation des champs de Sign Up
    fun signUp(name: String, prenme: String, phone: String, mail: String, password: String, isChecked: Boolean): Boolean {
        if (!name.isEmpty() && !prenme.isEmpty() && !phone.isEmpty() && !mail.isEmpty() && !password.isEmpty() && isChecked){
            return true
        }
        return false
    }
}