package com.carlos.supportcontactlist

import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        initializeViews()                                                                                                //Calls the function that initializes all views and their images as well as builds all necessary intents for the popup dialogs
        emailer()                                                                                                        //Calls the emailer function, which gives functionality to the floating action button (fab)
    }

    //Function to initialize variables as well as assign images to the various views
    private fun initializeViews(){

        //Variables assigned to each image view
        var xfinity = findViewById<ImageView>(R.id.xfinity_view)
        var amazon = findViewById<ImageView>(R.id.amazon_view)
        var att = findViewById<ImageView>(R.id.att_view)
        var netflix = findViewById<ImageView>(R.id.netflix_view)
        var hulu = findViewById<ImageView>(R.id.hulu_view)
        var chase = findViewById<ImageView>(R.id.chase_view)

        //Assigns pictures to image views
        xfinity.setImageResource(R.drawable.xfinity)
        amazon.setImageResource(R.drawable.amazon)
        att.setImageResource(R.drawable.att)
        netflix.setImageResource(R.drawable.netflix)
        hulu.setImageResource(R.drawable.hulu)
        chase.setImageResource(R.drawable.chase)

        netflix.setOnClickListener {                                                                                     //Brings up new activity as a floating dialog when icons are clicked - NOTE: Should make a single function for this
            val netflixIntent = Intent(this, pop::class.java)
            startActivity(netflixIntent)
        }

        att.setOnClickListener {                                                                                         //Brings up new activity as a floating dialog when icons are clicked - NOTE: Should make a single function for this
            val attIntent = Intent(this, attpop::class.java)
            startActivity(attIntent)
        }

        amazon.setOnClickListener {                                                                                      //Brings up new activity as a floating dialog when icons are clicked - NOTE: Should make a single function for this
            val amazonIntent = Intent(this, amazonpop::class.java)
            startActivity(amazonIntent)
        }

        xfinity.setOnClickListener {                                                                                     //Brings up new activity as a floating dialog when icons are clicked - NOTE: Should make a single function for this
            val xfinityIntent = Intent(this, xfinitypop::class.java)
            startActivity(xfinityIntent)
        }

        hulu.setOnClickListener {                                                                                        //Brings up new activity as a floating dialog when icons are clicked - NOTE: Should make a single function for this
            val huluIntent = Intent(this, hulupop::class.java)
            startActivity(huluIntent)
        }

        chase.setOnClickListener {                                                                                       //Brings up new activity as a floating dialog when icons are clicked - NOTE: Should make a single function for this
            val chaseIntent = Intent(this, chasepop::class.java)
            startActivity(chaseIntent)
        }
    }

    //Function that assigns use to the floating action button - in this case email chooser
    private fun emailer(){

        //Code below is for the Floating Action Button (Allows users to email developer)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Please send some feedback, if you can!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()                                                          //This specifies what should happen when someone clicks the FAB
            val to = "carlos.aguado94@gmail.com"                                                                        //Email address meant for developer, pre-fills 'To:' section in email
            val subject = "Suggestions for your app"                                                                    //Subject that is prefilled in email form
            val message = "Dear developer, "                                                                            //Beginning start of email message. Can be edited if need be by user

            val intent = Intent(Intent.ACTION_SEND)                                                                     //ACTION_SEND is meant for sending data from one activity to another
            val addresses = arrayOf(to)                                                                                 //Array of email addresses. In this case just a single one - the developer, me, Carlos

            intent.putExtra(Intent.EXTRA_EMAIL, addresses)                                                              //putExtra adds extra data to the intent - This one opens up the email app
            intent.putExtra(EXTRA_SUBJECT, subject)                                                                     //This is what puts the data from the previous variables as pre-filled
            intent.putExtra(EXTRA_TEXT, message)
            intent.setType("message/rfc822")
            startActivity(Intent.createChooser(intent, "Please pick an Emailing application:"))                    //The popup menu that asks which app you would want to use

        }
    }

    //Function that creates the option menu for logging out
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)                                                                    //"inflate" refers to bringing up the menu. So when the dots are tapped, the menu is "inflated" to be visible
        return true
    }

    //Function that gives actual use to tapping on the logout option
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val originalIntent = Intent(this, login::class.java)
        startActivity(originalIntent)
        Toast.makeText(applicationContext,"Succesfully logged out", Toast.LENGTH_LONG).show()   //Activates when the user hits the logout button, bringing them to the login screen
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
