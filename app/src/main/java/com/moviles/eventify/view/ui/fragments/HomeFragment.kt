package com.moviles.eventify.view.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.moviles.eventify.R
import com.moviles.eventify.data.preferences.PreferenceHelper

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceHelper = PreferenceHelper(requireContext())

        // 1. Mostrar saludo personalizado leyendo de SharedPreferences
        val tvHomeTitulo = view.findViewById<TextView>(R.id.tvHomeTitulo)
        val userName = preferenceHelper.getUserName()
        tvHomeTitulo.text = "¡Hola, $userName!"

        // 2. Vincular elementos de la UI
        val btnVerMas = view.findViewById<Button>(R.id.btnVerMas)
        val cardDevfest = view.findViewById<MaterialCardView>(R.id.cardEventDevfest)
        val cardAndroid = view.findViewById<MaterialCardView>(R.id.cardEventAndroid)
        val cardKotlin = view.findViewById<MaterialCardView>(R.id.cardEventKotlin)
        val btnHomeMenu = view.findViewById<ImageButton>(R.id.btnHomeMenu)

        // 3. Configurar listeners de clicks como ejemplo práctico
        btnVerMas.setOnClickListener {
            // Ejemplo de navegación usando Navigation Component
            findNavController().navigate(R.id.action_homeFragment_to_scheduleFragment)
            Toast.makeText(requireContext(), "Navegando al Cronograma", Toast.LENGTH_SHORT).show()
        }

        cardDevfest.setOnClickListener {
            // Ir a cronograma al pulsar un evento destacado
            findNavController().navigate(R.id.action_homeFragment_to_scheduleFragment)
            Toast.makeText(requireContext(), "Detalles de DevFest 2026", Toast.LENGTH_SHORT).show()
        }

        cardAndroid.setOnClickListener {
            Toast.makeText(requireContext(), "Detalles de Android Summit", Toast.LENGTH_SHORT).show()
        }

        cardKotlin.setOnClickListener {
            Toast.makeText(requireContext(), "Detalles de Kotlin Conf", Toast.LENGTH_SHORT).show()
        }

        btnHomeMenu.setOnClickListener {
            Toast.makeText(requireContext(), "Menú lateral no implementado", Toast.LENGTH_SHORT).show()
        }
    }
}
