package com.example.aprendiendo.firstapp


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.aprendiendo.R
import com.example.aprendiendo.firstapppackage.FirstAppActivity
import com.example.aprendiendo.firstapppackage.ThirdActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Referencias a los componentes de la UI
        val layout: LinearLayout = findViewById(R.id.secondLayout)
        val editTextName: EditText = findViewById(R.id.editTextName)
        val textViewName: TextView = findViewById(R.id.textViewName)
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val buttonThirdScreen: Button = findViewById(R.id.buttonThirdScreen)
        val buttonBackToMain: Button = findViewById(R.id.buttonBackToMain)
        val buttonChangeColorRed: Button = findViewById(R.id.buttonChangeColorRed)
        val buttonChangeColorBlue: Button = findViewById(R.id.buttonChangeColorBlue)

        // Listener para el botón de guardar
        buttonSave.setOnClickListener {
            val enteredName = editTextName.text.toString() // Obtener el nombre ingresado
            textViewName.text = "Nombre ingresado: $enteredName" // Mostrar el nombre en el TextView
        }

        // Listener para navegar a la tercera pantalla
        buttonThirdScreen.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        // Listener para volver a la pantalla de inicio
        buttonBackToMain.setOnClickListener {
            val intent = Intent(this, FirstAppActivity::class.java)
            startActivity(intent)
        }

        // Listener para cambiar el color del fondo a rojo
        buttonChangeColorRed.setOnClickListener {
            layout.setBackgroundColor(Color.RED)
        }

        // Listener para cambiar el color del fondo a azul
        buttonChangeColorBlue.setOnClickListener {
            layout.setBackgroundColor(Color.BLUE)
        }
    }
}

