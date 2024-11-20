package com.example.aprendiendo.firstapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.aprendiendo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var listView: ListView
    private val itemList = mutableListOf<String>()
    private val itemDetails = mutableMapOf<String, String>() // Map item name to details

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listView = view.findViewById(R.id.listView)

        // Configurar el adaptador de la lista
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemList)
        listView.adapter = adapter

        // Acciones al pulsar un item
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = itemList[position]
            val detailFragment = DetailFragment.newInstance(selectedItem, itemDetails[selectedItem] ?: "")
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        // Botón para agregar nuevos items
        val addButton = view.findViewById<FloatingActionButton>(R.id.addButton)
        addButton.setOnClickListener { showAddItemDialog(adapter) }

        return view
    }

    private fun showAddItemDialog(adapter: ArrayAdapter<String>) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_item, null)
        val itemNameInput = dialogView.findViewById<android.widget.EditText>(R.id.itemNameInput)
        val itemDetailInput = dialogView.findViewById<android.widget.EditText>(R.id.itemDetailInput)

        AlertDialog.Builder(requireContext())
            .setTitle("Agregar Nuevo Item")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val name = itemNameInput.text.toString()
                val detail = itemDetailInput.text.toString()
                if (name.isNotEmpty()) {
                    itemList.add(name)
                    itemDetails[name] = detail
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Item añadido: $name", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }
}
