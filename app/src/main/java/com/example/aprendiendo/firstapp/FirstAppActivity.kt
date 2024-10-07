package com.example.aprendiendo.firstapppackage


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.aprendiendo.R
import com.example.aprendiendo.firstapp.SecondActivity
import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.concurrent.Executors

class FirstAppActivity : AppCompatActivity() {

    private lateinit var greetingTextView: TextView

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        // Encuentra el TextView para el saludo
        greetingTextView = findViewById(R.id.greetingTextView)

        // Ejecutar la tarea en segundo plano usando Executor
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            // Obtener la hora en la zona horaria de España (Europe/Madrid)
            val spainZoneId = ZoneId.of("Europe/Madrid")
            val currentTimeInSpain = ZonedDateTime.now(spainZoneId)
            val currentHour = currentTimeInSpain.hour

            // Generar saludo según la hora
            val greeting = when (currentHour) {
                in 6..12 -> "Buenos días"
                in 13..18 -> "Buenas tardes"
                else -> "Buenas noches"
            }

            // Actualizar la UI en el hilo principal
            handler.post {
                greetingTextView.text = "$greeting (Hora en España: ${currentTimeInSpain.format(DateTimeFormatter.ofPattern("HH:mm"))})"
            }
        }

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


