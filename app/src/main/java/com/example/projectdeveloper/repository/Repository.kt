package com.example.projectdeveloper.repository

class Repository {
    fun login(user: String, password: String): Boolean {
        if (!user.isEmpty() && !password.isEmpty()){
            return true
        }
        return false
    }
    fun signUp(name: String, prenme: String, phone: String, mail: String, password: String, isChecked: Boolean): Boolean {
        if (!name.isEmpty() && !prenme.isEmpty() && !phone.isEmpty() && !mail.isEmpty() && !password.isEmpty() && isChecked){
            return true
        }
        return false
    }
}