package com.moviles.eventify.view.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moviles.eventify.R
import com.moviles.eventify.data.model.ScheduleItem
import com.moviles.eventify.model.Conference
import com.moviles.eventify.view.adapter.ScheduleAdapter

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Obtener referencia del RecyclerView
        val rvSchedule = view.findViewById<RecyclerView>(R.id.rvSchedule)
        
        // 2. Crear datos de prueba (Mock Data)
        val mockEvents = listOf(
            ScheduleItem(
                hour = "09:00",
                ampm = "AM",
                title = "Keynote: El futuro del Desarrollo Móvil",
                speaker = "María González",
                speakerDesc = "Google Developer Expert en Android",
                place = "Auditorio Principal",
                date = "Jueves 15 Mayo, 09:00 AM"
            ),
            ScheduleItem(
                hour = "10:30",
                ampm = "AM",
                title = "Taller: Jetpack Compose Avanzado",
                speaker = "Carlos Mendoza",
                speakerDesc = "Lead Mobile Architect en Globant",
                place = "Sala A - Segundo Piso",
                date = "Jueves 15 Mayo, 10:30 AM"
            ),
            ScheduleItem(
                hour = "12:00",
                ampm = "PM",
                title = "Sesión: Novedades en Kotlin 2.x",
                speaker = "Sebastián Gómez",
                speakerDesc = "Kotlin Developer Advocate en JetBrains",
                place = "Auditorio Principal",
                date = "Jueves 15 Mayo, 12:00 PM"
            ),
            ScheduleItem(
                hour = "03:00",
                ampm = "PM",
                title = "Conferencia: Seguridad en APIs Android",
                speaker = "Ana Laura López",
                speakerDesc = "CISO de Ciberseguridad en TechBank",
                place = "Sala B - Primer Piso",
                date = "Jueves 15 Mayo, 03:00 PM"
            )
        )

        // 3. Configurar LayoutManager y Adapter
        rvSchedule.layoutManager = LinearLayoutManager(requireContext())
        
        val adapter = ScheduleAdapter()
        adapter.listConference = ArrayList(mockEvents.map { item ->
            Conference().apply {
                title = item.title
                speaker = item.speaker
                description = item.speakerDesc
                this.tag = item.place
            }
        })
        
        rvSchedule.adapter = adapter

        // 5. Vincular otros elementos como botones
        val btnScheduleMenu = view.findViewById<ImageButton>(R.id.btnScheduleMenu)
        btnScheduleMenu.setOnClickListener {
            Toast.makeText(requireContext(), "Menú del Cronograma", Toast.LENGTH_SHORT).show()
        }
    }
}