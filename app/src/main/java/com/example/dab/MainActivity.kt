package com.example.dab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signUp=findViewById<Button>(R.id.signUp)
        val openNew=findViewById<TextView>(R.id.openNew)
        val name=findViewById<TextInputEditText>(R.id.name)
        val mail=findViewById<TextInputEditText>(R.id.mail)
        val pass=findViewById<TextInputEditText>(R.id.pass)
        val unid=findViewById<TextInputEditText>(R.id.unid)
        signUp.setOnClickListener{
            val nameFinal=name.text.toString().trim()
            val emailFinal=mail.text.toString().trim()
            val passFinal=pass.text.toString().trim()
            val fid=unid.text.toString().trim()

            val user=users(nameFinal,emailFinal,passFinal,fid)

            database=FirebaseDatabase.getInstance().getReference("users")
            if(nameFinal.isEmpty() || emailFinal.isEmpty() || passFinal.isEmpty() || fid.isEmpty()){
                Toast.makeText(this,"Please Fill all Fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            database.child(fid).setValue(user).addOnSuccessListener {

                Toast.makeText(this,"Successfully signed up",Toast.LENGTH_SHORT).show()
                name.text?.clear()
                mail.text?.clear()
                pass.text?.clear()
                unid.text?.clear()

            }.addOnFailureListener() {
                Toast.makeText(this,"failed to signed up",Toast.LENGTH_SHORT).show()
            }

        }
        openNew.setOnClickListener{
            val intent=Intent(applicationContext,signin::class.java)
            startActivity(intent)
        }

    }
}