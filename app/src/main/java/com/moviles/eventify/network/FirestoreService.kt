package com.moviles.eventify.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.moviles.eventify.model.Conference
import com.moviles.eventify.model.Speaker

const val CONFERENCES_COLLECTION_NAME="conferences"
const val CONFERENCE_COLLECTION_NAME="speakers"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    // val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
    //init{
    //    firebaseFirestore.firestoreSettings = settings
    //}
    
    fun getSpeakers(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection("speakers")
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                val list = result.toObjects(Speaker::class.java)
                callback.onSuccess(list)
            }
            .addOnFailureListener { exception ->
                callback.onFailed(exception)
            }
    }
    
    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection("conferences")
            .get()
            .addOnSuccessListener { result ->
                val list = result.toObjects(Conference::class.java)
                callback.onSuccess(list)
            }
            .addOnFailureListener { exception ->
                callback.onFailed(exception)
            }
    }
}