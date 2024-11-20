package com.example.aprendiendo.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aprendiendo.R
import com.example.aprendiendo.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ItemRepository.init(this) // Inicializar repositorio
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            loadFragment(ListFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
