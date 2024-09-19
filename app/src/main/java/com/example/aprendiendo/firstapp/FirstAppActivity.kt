package com.example.aprendiendo.firstapppackage

import com.example.aprendiendo.firstapp.SecondActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.aprendiendo.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        // Encuentra el botón por su ID
        val reciclarButton: Button = findViewById(R.id.reciclarButton)

        // Configura el click listener del botón
        reciclarButton.setOnClickListener {
            // Inicia la nueva actividad cuando el botón es presionado
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}


