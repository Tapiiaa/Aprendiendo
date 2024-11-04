package com.example.aprendiendo.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aprendiendo.R
import com.example.aprendiendo.firstapp.SecondActivity
import java.util.Calendar

class FirstAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        setGreeting()  // Set the greeting based on the time of day

        findViewById<Button>(R.id.reciclarButton).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    private fun setGreeting() {
        val greetingTextView = findViewById<TextView>(R.id.greetingTextView)
        val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        greetingTextView.text = when {
            currentTime < 12 -> "Buenos días"
            currentTime in 12..17 -> "Buenas tardes"
            else -> "Buenas noches"
        }
    }
}
