package com.carlos.supportcontactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.widget.TextView

class chasepop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chasepop)

        initializePop()
    }

    //Function to keep code private. Compartmentalizes class code so 3rd parties are unable to see
    private fun initializePop(){
        val text = findViewById<TextView>(R.id.chasesite)
        val text2 = findViewById<TextView>(R.id.chasenum)

        Linkify.addLinks(text, Linkify.ALL)
        Linkify.addLinks(text2, Linkify.ALL)
    }
}
