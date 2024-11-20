package com.example.aprendiendo.firstapp

import android.content.Context
import android.content.SharedPreferences

object ItemRepository {
    private const val PREFS_NAME = "ItemPrefs"
    private const val ITEMS_KEY = "items"
    private const val DESCRIPTIONS_KEY = "descriptions"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun addItem(name: String, description: String) {
        val items = getItems().toMutableList()
        val descriptions = getDescriptions().toMutableMap()

        items.add(name) // Agregar el nombre del ítem a la lista
        descriptions[name] = description // Asociar la descripción con el nombre

        saveItems(items)
        saveDescriptions(descriptions)
    }

    fun getItems(): List<String> {
        val itemsString = preferences.getString(ITEMS_KEY, "") ?: ""
        return if (itemsString.isEmpty()) emptyList() else itemsString.split(",").map { it.trim() }
    }

    fun getDescriptions(): Map<String, String> {
        val descriptionsString = preferences.getString(DESCRIPTIONS_KEY, "") ?: ""
        return if (descriptionsString.isEmpty()) emptyMap()
        else descriptionsString.split(";").associate {
            val parts = it.split("=")
            parts[0] to parts.getOrElse(1) { "" }
        }
    }

    fun saveItems(items: List<String>) {
        val itemsString = items.joinToString(",")
        preferences.edit().putString(ITEMS_KEY, itemsString).apply()
    }

    private fun saveDescriptions(descriptions: Map<String, String>) {
        val descriptionsString = descriptions.entries.joinToString(";") { "${it.key}=${it.value}" }
        preferences.edit().putString(DESCRIPTIONS_KEY, descriptionsString).apply()
    }
}
