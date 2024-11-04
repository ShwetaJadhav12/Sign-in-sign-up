package com.example.dab

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val mail=intent.getStringExtra(signin.KEYS1)
        val name=intent.getStringExtra(signin.KEYS2)
        val unid=intent.getStringExtra(signin.KEYS3)

        val welcomText=findViewById<TextView>(R.id.tWelcome)
        welcomText.text="Welcome $name"
        val showMail=findViewById<TextView>(R.id.showMail)
        showMail.text="Mail: $mail"
        val showId=findViewById<TextView>(R.id.showId)
        showId.text="Id: $unid"








    }
}