package com.tunahan.artbookhilttesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tunahan.artbookhilttesting.R
import com.tunahan.artbookhilttesting.databinding.FragmentArtsBinding


class ArtsFragment : Fragment(R.layout.fragment_arts) {

    private var fragmentBinding: FragmentArtsBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsBinding.bind(view)
        fragmentBinding = binding

        binding.fab.setOnClickListener {
            findNavController().navigate(ArtsFragmentDirections.actionArtsFragmentToArtDetailsFragment())
        }

    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}