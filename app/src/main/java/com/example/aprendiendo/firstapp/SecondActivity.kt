package com.example.aprendiendo.firstapp


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aprendiendo.R
import com.example.aprendiendo.firstapp.ThirdActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val name = findViewById<EditText>(R.id.editTextName).text.toString()
            getSharedPreferences("app_prefs", Context.MODE_PRIVATE).edit().apply {
                putString("user_name", name)
                apply()
            }
            findViewById<TextView>(R.id.textViewName).text = name
        }

        findViewById<Button>(R.id.buttonThirdScreen).setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}
