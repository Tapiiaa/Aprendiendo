package com.example.aprendiendo.firstapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.aprendiendo.R

class ItemAdapter(
    private val context: Context,
    private val items: MutableList<String>,
    private val favoriteItems: MutableList<String>,
    private val onItemsUpdated: () -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.itemName)
        val optionsButton: ImageButton = view.findViewById(R.id.optionsButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item

        holder.optionsButton.setOnClickListener {
            // Mostrar opciones en un diálogo
            AlertDialog.Builder(context)
                .setTitle("Opciones")
                .setItems(arrayOf("Marcar como favorito", "Eliminar ítem")) { _, which ->
                    when (which) {
                        0 -> {
                            if (!favoriteItems.contains(item)) {
                                favoriteItems.add(item)
                                Toast.makeText(context, "$item añadido a favoritos", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "$item ya está en favoritos", Toast.LENGTH_SHORT).show()
                            }
                        }
                        1 -> {
                            items.removeAt(position)
                            favoriteItems.remove(item)
                            notifyDataSetChanged()
                            onItemsUpdated()
                            Toast.makeText(context, "$item eliminado", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .show()
        }
    }

    override fun getItemCount(): Int = items.size
}
