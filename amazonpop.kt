package com.carlos.supportcontactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView

class amazonpop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amazonpop)

        intializePop()
    }

    //Function to keep code private. Compartmentalizes class code so 3rd parties are unable to see
    private fun intializePop(){
        val text = findViewById<TextView>(R.id.amazonsite)
        val text2 = findViewById<TextView>(R.id.amazonnum)

        Linkify.addLinks(text, Linkify.ALL)
        Linkify.addLinks(text2, Linkify.ALL)
    }
}
