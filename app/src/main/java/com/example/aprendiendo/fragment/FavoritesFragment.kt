package com.example.aprendiendo.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aprendiendo.R
import com.example.aprendiendo.firstapp.FavoritesAdapter

class FavoritesFragment : Fragment() {

    companion object {
        private const val ARG_FAVORITES = "favorites"

        fun newInstance(favorites: List<String>): FavoritesFragment {
            val fragment = FavoritesFragment()
            val args = Bundle()
            args.putStringArrayList(ARG_FAVORITES, ArrayList(favorites))
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var recyclerView: RecyclerView
    private val favoriteItems = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Recuperar favoritos pasados al fragmento
        arguments?.getStringArrayList(ARG_FAVORITES)?.let { favoriteItems.addAll(it) }

        // Configurar adaptador de favoritos
        val adapter = FavoritesAdapter(requireContext(), favoriteItems)
        recyclerView.adapter = adapter

        // Configurar botón de volver
        val backButton = view.findViewById<View>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
