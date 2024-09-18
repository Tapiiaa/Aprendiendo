package com.example.aprendiendo
// Variables
fun main(){
    println("Hello World") // Para imprimir en consola
    //Hay variables y valores

    val name = "Pedro Alonso" // val es para declarar una variable

    // --------------Valores numéricas----------------------

    val age : Int = 20 // : Int es para declarar el tipo de dato
    val number: Long = 30 // Long es para declarar el tipo de dato
    val numeroFloat: Float = 30.5f // Float es para declarar el tipo de dato
    val numeroDouble: Double = 30.5 // Double es para declarar el tipo de dato

    //--------- Imprimimos los valores ---------------
    println(name)
    println(age)
    println(number)
    println(numeroFloat)
    println(numeroDouble)

    // --------------Variables de texto----------------------

    val charEjemplo: Char = 'a' // Char es para declarar el tipo de dato (1 caracter)
    val stringEjemplo: String = "Hola" // String es para declarar el tipo de dato (varias caracteres)
    val boolExample : Boolean = true // Boolean es para declarar el tipo de dato (true o false)

    //--------- Imprimimos los valores ---------------
    println(charEjemplo)
    println(stringEjemplo)
    println(boolExample)

    //Crear las variables:
        // val --> valor
        // var --> variable

    var numero = 10
    println(numero) // Imprime el valor de la variable (10)
    numero = 20 // --> Podemos cambiar el valor de la variable a medida que el programa se ejecuta
    println(numero) // --> Ha cambiado el valor de la variable a 20

    val edad1 = 20
    val edad2 = 30
    // La suma de las edades debe ser 50

    // Operaciones aritméticas
    println("Sumar")
    println(edad1 + edad2)

    println("Restar")
    println(edad1 - edad2)

    println("Multiplicar")
    println(edad1 * edad2)

    println("Dividir")
    println(edad1 / edad2)

    println("Resto")
    println(edad1 % edad2)
    //Convertir un tipo de dato a otro
    // Int a Float
    //println(edad1.toFloat())

    // Llamado a una funcion hola mundo
    holaMundo() // --> Explicacion en la linea 74.

}
// Solo puede haber un fun main, si no da error.

//----------------- Funciones ----------------
// Estructura : fun nombreFuncion(parametros) : TipoDeDatoRetorno { codigo }
fun holaMundo(){
    println("Hola Mundo") // --> LLamamos en el main (Linea 66)
}
