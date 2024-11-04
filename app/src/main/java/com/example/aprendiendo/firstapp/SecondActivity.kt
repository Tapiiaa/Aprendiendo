package com.example.aprendiendo.firstapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aprendiendo.R
import com.example.aprendiendo.firstapp.FirstAppActivity
import com.example.aprendiendo.firstapp.ThirdActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val textViewName = findViewById<TextView>(R.id.textViewName)
        val layout = findViewById<LinearLayout>(R.id.secondLayout)

        // Guardar nombre y mostrarlo
        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val name = editTextName.text.toString()
            textViewName.text = name  // Muestra el nombre ingresado
        }

        // Cambiar el color de fondo a rojo
        findViewById<Button>(R.id.buttonChangeColorRed).setOnClickListener {
            layout.setBackgroundColor(Color.RED)
        }

        // Cambiar el color de fondo a azul
        findViewById<Button>(R.id.buttonChangeColorBlue).setOnClickListener {
            layout.setBackgroundColor(Color.BLUE)
        }

        // Volver a la pantalla de inicio
        findViewById<Button>(R.id.buttonBackToMain).setOnClickListener {
            val intent = Intent(this, FirstAppActivity::class.java)
            startActivity(intent)
        }

        // Ir a la tercera pantalla
        findViewById<Button>(R.id.buttonThirdScreen).setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}
