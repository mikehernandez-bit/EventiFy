package com.moviles.eventify.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.moviles.eventify.R
import com.moviles.eventify.data.preferences.PreferenceHelper

class PreferencesFragment : Fragment(R.layout.fragment_preferences) {
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceHelper = PreferenceHelper(requireContext())

        val etUserName = view.findViewById<EditText>(R.id.etUserName)
        val etUserEmail = view.findViewById<EditText>(R.id.etUserEmail)
        val cbNotification = view.findViewById<CheckBox>(R.id.cbNotifications)
        val btnSavePreferences = view.findViewById<Button>(R.id.btnSavePreferences)
        val btnShowPreferences = view.findViewById<Button>(R.id.btnShowPreferences)
        val btnClearPreferences = view.findViewById<Button>(R.id.btnClearPreferences)
        val tvPreferencesResult = view.findViewById<TextView>(R.id.tvPreferencesResult)

        btnSavePreferences.setOnClickListener {
            val name = etUserName.text.toString().trim()
            val email = etUserEmail.text.toString().trim()
            val notifications = cbNotification.isChecked

            if (name.isEmpty()) {
                etUserName.error = "Ingrese su nombre de usuario"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                etUserEmail.error = "Ingrese su correo"
                return@setOnClickListener
            }

            preferenceHelper.saveUserName(name)
            preferenceHelper.saveUserEmail(email)
            preferenceHelper.saveNotificationsEnabled(notifications)
            preferenceHelper.saveLastSection("Preferencias")

            Toast.makeText(
                requireContext(),
                "Preferencias Guardadas Correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }
        btnShowPreferences.setOnClickListener {
            val message = """
        Nombre: ${preferenceHelper.getUserName()}
        Correo: ${preferenceHelper.getUserEmail()}
        Notificaciones: ${if(preferenceHelper.areNotificationsEnabled()) "Activadas" else "Desactivada"}
        Última Sección: ${preferenceHelper.getLastSection()}
    """.trimIndent()
            tvPreferencesResult.text = message
        }
        btnClearPreferences.setOnClickListener {
            preferenceHelper.clearPreferences()

            etUserName.setText("")
            etUserEmail.setText("")
            cbNotification.isChecked = false
            tvPreferencesResult.text = "Preferencias Eliminadas"

            Toast.makeText(
                requireContext(),
                "Preferencias Eliminadas",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}