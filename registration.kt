package com.carlos.supportcontactlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*

class registration : AppCompatActivity() {

    //These are global variables. Utlizes kotlin-exclusive lateinit in order to tell the program we'll initialize them later
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var registerBtn: Button


    //Global variable for Firebase authentication
    private var mAuth: FirebaseAuth? = null

    //Private function used to conceal the code from 3rd parties - initializes global variables
    private fun initialize(){
        etFirstName = findViewById<EditText>(R.id.firstname)
        etLastName = findViewById<EditText>(R.id.lastname)
        etEmail = findViewById<EditText>(R.id.email)
        etPassword = findViewById<EditText>(R.id.password)
        registerBtn = findViewById<Button>(R.id.signup)
    }

    //Function that takes the user-input and creates a user for them in the online Firebase database
    private fun registerUser(){
        val email = etEmail.getText().toString()                                                                        //Makes user input into a single string
        val password = etPassword.getText().toString()                                                                  //Makes user input into a single string

        val loginIntent = Intent(this,login::class.java)                                                  //Intent that will take user back to login screen when successful

        if(!email.isEmpty() && !password.isEmpty()){                                                                    //Uses not(!) to check if the fields are empty. Won't allow registration if any are
            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if(task.isSuccessful) {
                    val user = mAuth!!.currentUser
                    startActivity(loginIntent)
                    Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error: Password should be at least 6 characters", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
        }
    }







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mAuth = FirebaseAuth.getInstance()

        initialize()

        registerBtn.setOnClickListener {
                registerUser()
        }
    }
}
