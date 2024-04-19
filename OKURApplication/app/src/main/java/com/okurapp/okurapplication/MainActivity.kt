package com.okurapp.okurapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Views
        usernameEditText = findViewById(R.id.enterUserName)
        passwordEditText = findViewById(R.id.enterPassword)
        loginButton = findViewById(R.id.buttonLogIn)

        // Firebase database reference
        database = FirebaseDatabase.getInstance().reference

        // Login button click listener
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            } else {
                // Check if the entered username exists in the database
                val userQuery: Query = database.orderByChild("createUserName").equalTo(username)
                userQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (userSnapshot in dataSnapshot.children) {

                                val user = userSnapshot.getValue(User::class.java)
                                // Compare entered password with the stored password
                                if (user?.createPassword == password) {
                                    // Login successful
                                    Toast.makeText(this@MainActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@MainActivity, MainWelcome::class.java)
                                    startActivity(intent)
                                    finish() // Optional: Close the login activity
                                } else {
                                    // Incorrect password
                                    Toast.makeText(this@MainActivity, "Incorrect password", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            // User not found
                            Toast.makeText(this@MainActivity, "User not found", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Toast.makeText(this@MainActivity, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        // Register button click listener
        val registerButton: Button = findViewById(R.id.buttonRegister)
        registerButton.setOnClickListener {
            val intent = Intent(this, MainRegister::class.java)
            startActivity(intent)
        }
    }
}
