package com.example.aprendiendo.firstapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.aprendiendo.database.UserDatabaseHelper
import com.example.aprendiendo.R

class ThirdActivity : AppCompatActivity() {

    private lateinit var dbHelper: UserDatabaseHelper
    private lateinit var barChartContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        dbHelper = UserDatabaseHelper(this)
        barChartContainer = findViewById(R.id.barChartContainer)

        findViewById<Button>(R.id.buttonSaveUser).setOnClickListener {
            val name = findViewById<EditText>(R.id.userNameInput).text.toString()
            val age = findViewById<EditText>(R.id.userAgeInput).text.toString().toInt()
            saveUser(name, age)
            addBarToChart(age)
        }
    }

    private fun saveUser(name: String, age: Int) {
        // Lógica para guardar usuario en la base de datos, incluyendo la edad
    }

    private fun addBarToChart(age: Int) {
        val barView = View(this).apply {
            layoutParams = LinearLayout.LayoutParams(0, age * 10, 1.0f)
            setBackgroundColor(Color.BLUE)
        }
        barChartContainer.addView(barView)
    }
}
