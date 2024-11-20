package com.example.aprendiendo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.aprendiendo.R

class DetailFragment : Fragment() {

    companion object {
        private const val ARG_ITEM_NAME = "itemName"
        private const val ARG_ITEM_DETAILS = "itemDetails"

        fun newInstance(name: String, details: String): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString(ARG_ITEM_NAME, name)
            args.putString(ARG_ITEM_DETAILS, details)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val itemNameTextView = view.findViewById<TextView>(R.id.itemNameTextView)
        val itemDetailsTextView = view.findViewById<TextView>(R.id.itemDetailsTextView)
        val backButton = view.findViewById<Button>(R.id.backButton)

        val name = arguments?.getString(ARG_ITEM_NAME)
        val details = arguments?.getString(ARG_ITEM_DETAILS)

        itemNameTextView.text = name
        itemDetailsTextView.text = details

        // Configurar botón para regresar al fragmento de lista
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
