package com.example.aprendiendo.firstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aprendiendo.R

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
    private val favorites = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        arguments?.getStringArrayList(ARG_FAVORITES)?.let { favorites.addAll(it) }

        val adapter = ItemAdapter(requireContext(), favorites, mutableListOf()) {}
        recyclerView.adapter = adapter

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

}
