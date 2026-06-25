package com.moviles.eventify

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.moviles.eventify.model.Speaker
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 1. Setup navigation controller
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragContent) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnvMenu)
        bottomNavigationView.setupWithNavController(navController)

        // 2. Parse JSON Data
        val jsonArr = JSONArray("[" +
                "            {" +
                "                'biography' : 'Especialista en desarrollo Android con Kotlin, arquitectura MVVM, Jetpack y buenas prácticas para aplicaciones móviles escalables.'," +
                "                'category' : 1," +
                "                'image' : 'https://randomuser.me/api/portraits/women/44.jpg'," +
                "                'jobtitle' : 'Android Developer'," +
                "                'name' : 'María González'," +
                "                'twitter' : 'mariagdev'," +
                "                'workplace' : 'Google Developer Groups'" +
                "            }," +
                "            {" +
                "                'biography' : 'Ingeniero de software especializado en Firebase, Cloud Firestore, autenticación móvil y servicios backend para aplicaciones Android'," +
                "                'category' : 2," +
                "                'image' : 'https://randomuser.me/api/portraits/men/32.jpg'," +
                "                'jobtitle' : 'Firebase Specialist'," +
                "                'name' : 'Juan Pérez'," +
                "                'twitter' : 'juanfirebase'," +
                "                'workplace' : 'Firebase LATAM'" +
                "            }," +
                "            {" +
                "                'biography' : 'Investigadora en inteligencia artificial aplicada a aplicaciones móviles, machine learning y experiencias inteligentes para usuarios.'," +
                "                'category' : 3," +
                "                'image' : 'https://randomuser.me/api/portraits/women/65.jpg'," +
                "                'jobtitle' : 'AI & ML Engineer'," +
                "                'name' : 'Ana Torres'," +
                "                'twitter' : 'anatorresai'," +
                "                'workplace' : 'Tech AI Lab'" +
                "            }," +
                "            {" +
                "                'biography' : 'Diseñador UX/UI enfocado en interfaces móviles, experiencia de usuario, accesibilidad y diseño basado en Material Design.'," +
                "                'category' : 4," +
                "                'image' : 'https://randomuser.me/api/portraits/men/45.jpg'," +
                "                'jobtitle' : 'UX/UI Designer'," +
                "                'name' : 'Carlos Ruiz'," +
                "                'twitter' : 'carlosux'," +
                "                'workplace' : 'Design Mobile Studio'" +
                "            }," +
                "            {" +
                "                'biography' : 'Arquitecto cloud especializado en Google Cloud Platform, integración de servicios en la nube y soluciones móviles escalables.'," +
                "                'category' : 5," +
                "                'image' : 'https://randomuser.me/api/portraits/men/71.jpg'," +
                "                'jobtitle' : 'Cloud Architect'," +
                "                'name' : 'Luis Morales'," +
                "                'twitter' : 'luismcloud'," +
                "                'workplace' : 'Google Cloud Partner'" +
                "            }," +
                "            {" +
                "                'biography' : 'Desarrolladora móvil especializada en Kotlin, consumo de API REST, persistencia local con Room y publicación de apps Android.'," +
                "                'category' : 6," +
                "                'image' : 'https://randomuser.me/api/portraits/women/22.jpg'," +
                "                'jobtitle' : 'Mobile Developer'," +
                "                'name' : 'Sofía Vega'," +
                "                'twitter' : 'sofiadev'," +
                "                'workplace' : 'Android Academy'" +
                "            }" +
                "        ]")

        // 3. Upload to Firebase Firestore
        val firebaseFirestore = FirebaseFirestore.getInstance()
        for (i in 0 until jsonArr.length()) {
            val aux = jsonArr.getJSONObject(i)
            val name = aux.getString("name")

            val speaker = Speaker()
            speaker.name = name
            speaker.jobTitle = aux.getString("jobtitle")
            speaker.workPlace = aux.getString("workplace")
            speaker.biography = aux.getString("biography")
            speaker.twitter = aux.getString("twitter")
            speaker.image = aux.getString("image")
            speaker.category = aux.getInt("category")

            // Use set() with name as document ID to avoid duplicate speakers on app restart
            firebaseFirestore.collection("speakers")
                .document(name)
                .set(speaker)
                .addOnSuccessListener {
                    Log.d("FirestoreUpload", "Speaker $name uploaded successfully!")
                }
                .addOnFailureListener { e ->
                    Log.e("FirestoreUpload", "Error uploading speaker $name: ${e.message}")
                }
        }
        Toast.makeText(this, "Enviando datos a Firebase...", Toast.LENGTH_SHORT).show()
    }
}