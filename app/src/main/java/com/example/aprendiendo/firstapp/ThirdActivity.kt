package com.example.aprendiendo.firstapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aprendiendo.R

class ThirdActivity : AppCompatActivity() {
    private lateinit var barChartContainer: LinearLayout
    private lateinit var dataDisplay: TextView
    private val ageCounts = mutableMapOf<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        barChartContainer = findViewById(R.id.barChartContainer)
        dataDisplay = findViewById(R.id.dataDisplay)

        findViewById<Button>(R.id.buttonSaveUser).setOnClickListener {
            val name = findViewById<EditText>(R.id.userNameInput).text.toString()
            val age = findViewById<EditText>(R.id.userAgeInput).text.toString().toInt()
            updateBarChart(age)
            updateDataDisplay(name, age)
        }
    }

    private fun updateBarChart(age: Int) {
        val currentCount = ageCounts.getOrDefault(age, 0)
        ageCounts[age] = currentCount + 1

        barChartContainer.removeAllViews() // Clear existing bars and labels

        ageCounts.forEach { (age, count) ->
            val barView = TextView(this).apply {
                layoutParams = LinearLayout.LayoutParams(0, count * 100, 1.0f)
                text = age.toString()
                textSize = 16f
                setBackgroundColor(0xFF0000FF.toInt())  // Set bar color to blue
                gravity = LinearLayout.VERTICAL
            }
            barChartContainer.addView(barView)
        }
    }

    private fun updateDataDisplay(name: String, age: Int) {
        val currentText = dataDisplay.text.toString()
        dataDisplay.text = "${currentText}${name}, $age\n"
    }
}
