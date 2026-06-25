package com.moviles.eventify

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.moviles.eventify.model.Conference
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import org.json.JSONObject
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragContent) as NavHostFragment

        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnvMenu)
        bottomNavigationView.setupWithNavController(navController)

        val jsonArr2 = JSONArray("""
            [
                {
                    "datetime" : 1778845200,
                    "description" : "Conferencia magistral sobre las tendencias, herramientas y arquitecturas modernas que están transformando el desarrollo de aplicaciones móviles.",
                    "speaker" : "María González",
                    "tag" : "Mobile",
                    "title" : "Keynote: El futuro del Desarrollo Móvil"
                },
                {
                    "datetime" : 1778852400,
                    "description" : "Workshop práctico sobre Kotlin Multiplatform y su importancia para desarrollar soluciones móviles compartidas entre Android, iOS y otras plataformas. ",
                    "speaker" : "Sofía Vega",
                    "tag" : "Kotlin",
                    "title" : "Kotlin Multiplatform Workshop"
                },
                {
                    "datetime" : 1778863200,
                    "description" : "Sesión aplicada sobre el uso de inteligencia artificial y machine learning en aplicaciones móviles modernas.",
                    "speaker" : "Ana Torres",
                    "tag" : "IA",
                    "title" : "Inteligencia Artificial en Apps."
                },
                {
                    "datetime" : 1778870400,
                    "description" : "Conferencia sobre el uso de Firebase, Cloud Firestore y servicios cloud para crear aplicaciones móviles conectadas y escalables.",
                    "speaker" : "Juan Pérez",
                    "tag" : "Firebase",
                    "title" : "Cloud & Firebase para móviles"
                },
                {
                    "datetime" : 1778950800,
                    "description" : "Charla sobre buenas prácticas de diseño de interfaces móviles, accesibilidad, usabilidad y experiencia de usuario.",
                    "speaker" : "Carlos Ruiz",
                    "tag" : "UX/UI",
                    "title" : "Diseño de experiencias móviles con Material Design"
                },
                {
                    "datetime" : 1778958000,
                    "description" : "Conferencia sobre arquitectura cloud, integración de servicios y despliegue de aplicaciones móviles con infraestructura escalable. ",
                    "speaker" : "Luis Morales",
                    "tag" : "Cloud",
                    "title" : "Arquitectura Cloud para aplicaciones móviles"
                }
            ]
        """.trimIndent())

        val firebaseFirestore = FirebaseFirestore.getInstance()
        for (i in 0 until jsonArr2.length()) {
            val aux = jsonArr2.get(i) as JSONObject
            var conference = Conference()
            conference.title = aux.getString("title")
            conference.description = aux.getString("description")
            conference.tag = aux.getString("tag")
            val cal = Calendar.getInstance()
            cal.timeInMillis = aux.getLong("datetime") * 1000
            conference.dateTime = cal.time
            conference.speaker = aux.getString("speaker")

            firebaseFirestore.collection("Conferences").document().set(conference)
        }
    }
}