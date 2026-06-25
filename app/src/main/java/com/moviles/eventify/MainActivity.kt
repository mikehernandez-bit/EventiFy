package com.moviles.eventify

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.moviles.eventify.model.Speaker
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        /* val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragContent) as NavHostFragment

        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnvMenu)
        bottomNavigationView.setupWithNavController(navController)
        */
        val jsonArr = JSONArray(
            "[\n" +
                    "            {\n" +
                    "                'biography' : 'Especialista en desarrollo Android con Kotlin, arquitectura MVVM, Jetpack y buenas prácticas para aplicaciones móviles escalables.',\n" +
                    "                'category' : 1,\n" +
                    "                'image' : 'https://randomuser.me/api/portraits/women/44.jpg',\n" +
                    "                'jobtitle' : 'Android Developer',\n" +
                    "                'name' : 'AMaría González',\n" +
                    "                'twitter' : 'mariagdev',\n" +
                    "                'workplace' : 'Google Developer Groups'\n" +
                    "            },\n" +
                    "            {\n" +
                    "                'biography' : 'Ingeniero de software especializado en Firebase, Cloud Firestore, autenticación móvil y servicios backend para aplicaciones Android',\n" +
                    "                'category' : 2,\n" +
                    "                'image' : 'https://randomuser.me/api/portraits/men/32.jpg',\n" +
                    "                'jobtitle' : 'Firebase Specialist',\n" +
                    "                'name' : 'Juan Pérez',\n" +
                    "                'twitter' : 'juanfirebase',\n" +
                    "                'workplace' : 'Firebase LATAM'\n" +
                    "            },\n" +
                    "            {\n" +
                    "                'biography' : 'Investigadora en inteligencia artificial aplicada a aplicaciones móviles, machine learning y experiencias inteligentes para usuarios.',\n" +
                    "                'category' : 3,\n" +
                    "                'image' : 'https://randomuser.me/api/portraits/women/65.jpg',\n" +
                    "                'jobtitle' : 'AI & ML Engineer',\n" +
                    "                'name' : 'Ana Torres',\n" +
                    "                'twitter' : 'anatorresai',\n" +
                    "                'workplace' : 'Tech AI Lab'\n" +
                    "            },\n" +
                    "            {\n" +
                    "                'biography' : 'Diseñador UX/UI enfocado en interfaces móviles, experiencia de usuario, accesibilidad y diseño basado en Material Design.',\n" +
                    "                'category' : 4,\n" +
                    "                'image' : 'https://randomuser.me/api/portraits/men/45.jpg',\n" +
                    "                'jobtitle' : 'UX/UI Designer',\n" +
                    "                'name' : 'Carlos Ruiz',\n" +
                    "                'twitter' : 'carlosux',\n" +
                    "                'workplace' : 'Design Mobile Studio'\n" +
                    "            },\n" +
                    "            {\n" +
                    "                'biography' : 'Arquitecto cloud especializado en Google Cloud Platform, integración de servicios en la nube y soluciones móviles escalables.',\n" +
                    "                'category' : 5,\n" +
                    "                'image' : 'https://randomuser.me/api/portraits/men/71.jpg',\n" +
                    "                'jobtitle' : 'Cloud Architect',\n" +
                    "                'name' : 'Luis Morales',\n" +
                    "                'twitter' : 'luismcloud',\n" +
                    "                'workplace' : 'Google Cloud Partner'\n" +
                    "            },\n" +
                    "            {\n" +
                    "                'biography' : 'Desarrolladora móvil especializada en Kotlin, consumo de API REST, persistencia local con Room y publicación de apps Android.',\n" +
                    "                'category' : 6,\n" +
                    "                'image' : 'https://randomuser.me/api/portraits/women/22.jpg',\n" +
                    "                'jobtitle' : 'Mobile Developer',\n" +
                    "                'name' : 'Sofía Vega',\n" +
                    "                'twitter' : 'sofiadev',\n" +
                    "                'workplace' : 'Android Academy'\n" +
                    "            },\n" +
                    "        ]"
        )

        val firebaseFirestore = FirebaseFirestore.getInstance()
        for (i in 0 until jsonArr.length()){
            val aux= jsonArr.get(i) as JSONObject
            var speaker= Speaker()
            speaker.name= aux.getString("name")
            speaker.jobTitle= aux.getString("jobtitle")
            speaker.workPlace= aux.getString("workplace")
            speaker.biography= aux.getString("biography")
            speaker.twitter= aux.getString("twitter")
            speaker.image= aux.getString("image")
            speaker.category= aux.getInt("category")
            firebaseFirestore.collection("speakers").document().set(speaker)
        }
    }
}