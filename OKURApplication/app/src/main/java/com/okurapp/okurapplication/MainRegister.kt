package com.okurapp.okurapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register)

        //create database

        var database = FirebaseDatabase.getInstance().reference

        //button and variable definitions
        var saveBtn: Button = findViewById(R.id.buttonCreateSave)
        var createUserName: TextView = findViewById(R.id.createUserName)
        var createPassword: TextView = findViewById(R.id.createPassword)


        saveBtn.setOnClickListener {

            val username = createUserName.text.toString()
            val password = createPassword.text.toString()

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            } else {

                //register firebase
                database.child(username).setValue(User(username, password))

                //registration control
                Toast.makeText(applicationContext, "Registration Successful ", Toast.LENGTH_LONG)
                    .show()
            }

        }

        var backTologinPageBtn: Button = findViewById(R.id.buttonBackLoginPage)
        backTologinPageBtn.setOnClickListener {
            val switchBackToLogin = Intent(applicationContext, MainActivity::class.java)
            startActivity(switchBackToLogin)
        }

    }
}