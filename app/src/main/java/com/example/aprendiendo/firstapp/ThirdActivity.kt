package com.example.aprendiendo.firstapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aprendiendo.R
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

class ThirdActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var barChartContainer: LinearLayout
    private lateinit var dataDisplay: TextView
    private val ageCounts = mutableMapOf<Int, Int>()
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    @RequiresApi(Build.VERSION_CODES.N)
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

        // Initialize accelerometer
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (Math.sqrt((it.values[0] * it.values[0] + it.values[1] * it.values[1] + it.values[2] * it.values[2]).toDouble()) > 15) {
                findViewById<LinearLayout>(R.id.thirdActivityLayout).setBackgroundColor(Color.GREEN)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not used
    }

    @RequiresApi(Build.VERSION_CODES.N)
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
