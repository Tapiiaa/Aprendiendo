package com.example.aprendiendo.firstapp

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.aprendiendo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val mutableItemList = mutableListOf<String>() // Lista mutable para el adaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listView = view.findViewById(R.id.listView)

        // Inicializar la lista mutable desde ItemRepository
        mutableItemList.addAll(ItemRepository.getItems())

        // Configurar el adaptador con la lista mutable
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            mutableItemList
        )
        listView.adapter = adapter

        // Configurar clics en los items
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = adapter.getItem(position)
            val description = "Descripción del item: $selectedItem" // Aquí puedes ajustar según los datos reales

            val detailFragment = DetailFragment.newInstance(selectedItem ?: "", description)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .addToBackStack(null)
                .commit()
        }


        // Botón flotante para añadir items
        val addButton = view.findViewById<FloatingActionButton>(R.id.addButton)
        addButton.setOnClickListener { showAddItemDialog() }

        return view
    }

    private fun showAddItemDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_item, null)
        val itemNameInput = dialogView.findViewById<android.widget.EditText>(R.id.itemNameInput)

        AlertDialog.Builder(requireContext())
            .setTitle("Agregar Nuevo Item")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val name = itemNameInput.text.toString()
                if (name.isNotEmpty()) {
                    ItemRepository.addItem(name) // Guardar en repositorio

                    // Actualizar la lista mutable y el adaptador
                    mutableItemList.clear()
                    mutableItemList.addAll(ItemRepository.getItems())
                    adapter.notifyDataSetChanged() // Notificar cambios al adaptador

                    updateWidget() // Actualizar widget
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }

    private fun updateWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(requireContext())
        val widgetProvider = ComponentName(requireContext(), WidgetProvider::class.java)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(widgetProvider)

        val intent = Intent(requireContext(), WidgetProvider::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        requireContext().sendBroadcast(intent)
    }
}
