package com.example.aprendiendo.firstapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.aprendiendo.R
import com.example.aprendiendo.fragment.DetailFragment

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

        // Configurar clic en el nombre del ítem para redirigir al DetailFragment
        holder.itemName.setOnClickListener {
            val description = ItemRepository.getDescriptions()[item] ?: "Sin descripción disponible"
            val fragment = DetailFragment.newInstance(item, description)

            // Cargar el fragmento de detalles
            (context as? MainActivity)?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        // Configurar el botón de opciones
        holder.optionsButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Opciones")
                .setItems(arrayOf("Marcar como favorito", "Eliminar ítem")) { _, which ->
                    when (which) {
                        0 -> {
                            if (!favoriteItems.contains(item)) {
                                favoriteItems.add(item)
                            }
                        }
                        1 -> {
                            items.removeAt(position)
                            favoriteItems.remove(item)
                            notifyDataSetChanged()
                            onItemsUpdated()
                        }
                    }
                }
                .show()
        }
    }

    override fun getItemCount(): Int = items.size
}
