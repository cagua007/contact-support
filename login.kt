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
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    //Below are global variables, utilizing kotlin-exclusive lateinit in order to let program know we will initialize them later
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var loginBtn: Button
    private lateinit var signupBtn: Button

    //Variable for Firebase authentication
    private lateinit var mAuth: FirebaseAuth

    //Function to keep code private. Initializes global variables
    private fun initialize(){
        etEmail = findViewById(R.id.loginemail)
        etPassword = findViewById(R.id.loginpass)
        loginBtn = findViewById(R.id.login)
        signupBtn = findViewById(R.id.signup)
    }

    //Function checks credentials entered by user and either logs them in or displays appropriate message
    private fun loginUserAccount(){

        val loginEmail = etEmail.getText().toString()                                                                               //Makes user text into a single string
        val loginPass = etPassword.getText().toString()                                                                             //Makes user text into a single string

        if(TextUtils.isEmpty(loginEmail)){                                                                                          //If statement checks if credential fields are empty
            Toast.makeText(applicationContext, "Please enter your login email", Toast.LENGTH_LONG).show()
            return
        }
        if(TextUtils.isEmpty(loginPass)){
            Toast.makeText(applicationContext,"Please enter your login password", Toast.LENGTH_LONG).show()
            return
        }

        mAuth.signInWithEmailAndPassword(loginEmail, loginPass)                                                                      //Checks Firebase for matching email/password strings, either logs in or displays error
            .addOnCompleteListener{task: Task<AuthResult> ->
                if(task.isSuccessful){
                    Toast.makeText(applicationContext,"Login success!", Toast.LENGTH_SHORT).show()                              //Toast function creates temporary popup dialog for app actions

                    val mainPageIntent = Intent(this, MainActivity::class.java)                                        //Intent allows app to switch to new activities
                    startActivity(mainPageIntent)                                                                                    //startActivity opens the desired intent/activity
                } else {
                    Toast.makeText(applicationContext,"Login failed, please check credentials", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //Main function, ideally should only house method calls or button click listeners
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()                                                                                             //Retrieves new instance of firebase authentication

        initialize()                                                                                                                   //calls the method to initialize all variables

        loginBtn.setOnClickListener {                                                                                                  //Determines when a button is touched
            loginUserAccount()                                                                                                         //Method to log the user in
        }

        signupBtn.setOnClickListener {
            val registerIntent = Intent(this, registration::class.java)
            startActivity(registerIntent)
        }
    }
}
