package com.example.mipropiaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mipropiaapp.ui.theme.MiPropiaAppTheme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInicial = findViewById<Button>(R.id.btnInicial)
        btnInicial.setOnClickListener {
            val intent = Intent(this, IngresaNombreActivity::class.java)
            startActivity(intent)
        }
    }
}

@Composable
fun BotonInicial(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(onClick = { navController.navigate("ingresaNombre") }) {
        Text("Ingresa tu nombre")
    }
}

@Preview(showBackground = true)
@Composable
fun BotonInicialPreview() {
    MiPropiaAppTheme {
        val navController = androidx.navigation.compose.rememberNavController()
        BotonInicial(navController)
    }
}

@Composable
fun IngresaNombrePantalla(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }

    Column {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Ingresa tu nombre") }
        )
    }
}