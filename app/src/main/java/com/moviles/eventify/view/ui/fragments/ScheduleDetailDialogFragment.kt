package com.moviles.eventify.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.moviles.eventify.R

class ScheduleDetailDialogFragment : DialogFragment() {

    private var title: String? = null
    private var date: String? = null
    private var ubication: String? = null
    private var speaker: String? = null
    private var speakerDesc: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            date = it.getString(ARG_DATE)
            ubication = it.getString(ARG_UBICATION)
            speaker = it.getString(ARG_SPEAKER)
            speakerDesc = it.getString(ARG_SPEAKER_DESC)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Vincular vistas del layout de detalle
        val tvTitle = view.findViewById<TextView>(R.id.tvScheduleDetailTitle)
        val tvCalendar = view.findViewById<TextView>(R.id.tvEventCalendar)
        val tvUbication = view.findViewById<TextView>(R.id.tvEventUbication)
        val tvSpeaker = view.findViewById<TextView>(R.id.tvEventPersonal)
        val tvSpeakerDesc = view.findViewById<TextView>(R.id.tvEventPersonalDescripcion)
        val ivClose = view.findViewById<ImageView>(R.id.ivCancelar)
        val ivShare = view.findViewById<ImageView>(R.id.ivCompartir)

        // 2. Rellenar con los argumentos si existen
        tvTitle.text = title ?: "Título no disponible"
        tvCalendar.text = date ?: "Fecha no disponible"
        tvUbication.text = ubication ?: "Ubicación no disponible"
        tvSpeaker.text = speaker ?: "Ponente no disponible"
        tvSpeakerDesc.text = speakerDesc ?: ""

        // 3. Cerrar el diálogo al pulsar la X
        ivClose.setOnClickListener {
            dismiss()
        }

        // 4. Botón de compartir simulado
        ivShare.setOnClickListener {
            Toast.makeText(requireContext(), "Compartiendo evento: $title", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_DATE = "arg_date"
        private const val ARG_UBICATION = "arg_ubication"
        private const val ARG_SPEAKER = "arg_speaker"
        private const val ARG_SPEAKER_DESC = "arg_speaker_desc"

        @JvmStatic
        fun newInstance(
            title: String,
            date: String,
            ubication: String,
            speaker: String,
            speakerDesc: String
        ) = ScheduleDetailDialogFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_DATE, date)
                putString(ARG_UBICATION, ubication)
                putString(ARG_SPEAKER, speaker)
                putString(ARG_SPEAKER_DESC, speakerDesc)
            }
        }
    }
}