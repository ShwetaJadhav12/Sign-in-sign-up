package com.example.dab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signin : AppCompatActivity() {
    lateinit var database: DatabaseReference
    companion object {
        const val KEYS1 = "com.example.dab.signin.email"
        const val KEYS2 = "com.example.dab.signin.name"
        const val KEYS3 = "com.example.dab.signin.unid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val signIn = findViewById<Button>(R.id.signIn)
        val name = findViewById<TextInputEditText>(R.id.loginName)

        signIn.setOnClickListener {
            val nameString = name.text.toString()
            if (nameString.isEmpty()) {
                Toast.makeText(this, "Please enter unique ID", Toast.LENGTH_SHORT).show()
            } else {
                readData(nameString)
                    }
                }
            }

    private fun readData(nameString: String) {
        database = FirebaseDatabase.getInstance().getReference("users")
        database.child(nameString).get().addOnSuccessListener {
            if (it.exists()) {
                val email = it.child("mail").value
                val name = it.child("name").value
                val unid = it.child("unid").value

                val intent = Intent(applicationContext, welcome::class.java)
                    intent.putExtra(KEYS1, email.toString())
                    intent.putExtra(KEYS2, name.toString())
                    intent.putExtra(KEYS3, unid.toString()) // Use KEYS3 for unique ID

                    startActivity(intent)
                }

                    else {
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }
}



