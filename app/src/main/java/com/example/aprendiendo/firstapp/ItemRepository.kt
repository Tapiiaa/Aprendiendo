package com.example.aprendiendo.firstapp

import android.content.Context
import android.content.SharedPreferences

object ItemRepository {
    private const val PREFS_NAME = "ItemPrefs"
    private const val ITEMS_KEY = "items"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun addItem(item: String) {
        val items = getItems().toMutableList()
        items.add(item)
        saveItems(items)
    }

    fun getItems(): List<String> {
        val itemsString = preferences.getString(ITEMS_KEY, "") ?: ""
        return if (itemsString.isEmpty()) emptyList() else itemsString.split(",").map { it.trim() }
    }

    private fun saveItems(items: List<String>) {
        val itemsString = items.joinToString(",")
        preferences.edit().putString(ITEMS_KEY, itemsString).apply()
    }
}
