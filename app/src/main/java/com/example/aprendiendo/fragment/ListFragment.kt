package com.example.aprendiendo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aprendiendo.R
import com.example.aprendiendo.firstapp.ItemAdapter
import com.example.aprendiendo.firstapp.ItemRepository

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private val mutableItemList = mutableListOf<String>() // Lista principal
    private val favoriteItems = mutableListOf<String>() // Lista de favoritos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializar datos desde el repositorio
        mutableItemList.clear()
        mutableItemList.addAll(ItemRepository.getItems())

        // Configurar adaptador
        adapter = ItemAdapter(requireContext(), mutableItemList, favoriteItems) {
            ItemRepository.saveItems(mutableItemList) // Guardar cambios en el repositorio
        }
        recyclerView.adapter = adapter

        // Configurar botón para agregar ítems
        val addButton = view.findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener { showAddItemDialog() }

        // Configurar botón para ver favoritos
        val favoritesButton = view.findViewById<Button>(R.id.favoritesButton)
        favoritesButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FavoritesFragment.newInstance(favoriteItems))
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    private fun showAddItemDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_item, null)
        val itemNameInput = dialogView.findViewById<android.widget.EditText>(R.id.itemNameInput)
        val itemDescriptionInput = dialogView.findViewById<android.widget.EditText>(R.id.itemDescriptionInput)

        AlertDialog.Builder(requireContext())
            .setTitle("Agregar Nuevo Item")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val name = itemNameInput.text.toString()
                val description = itemDescriptionInput.text.toString()

                if (name.isNotEmpty()) {
                    // Guardar ítem y descripción en el repositorio
                    ItemRepository.addItem(name, description)
                    mutableItemList.clear()
                    mutableItemList.addAll(ItemRepository.getItems())
                    adapter.notifyDataSetChanged() // Actualizar adaptador
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }

    override fun onResume() {
        super.onResume()
        // Actualizar lista al volver al fragmento
        mutableItemList.clear()
        mutableItemList.addAll(ItemRepository.getItems())
        adapter.notifyDataSetChanged()
    }
}
