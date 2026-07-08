package com.moviles.eventify.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moviles.eventify.model.Speaker
import com.moviles.eventify.network.Callback
import com.moviles.eventify.network.FirestoreService

class SpeakerViewModel : ViewModel() {
    val firestoreService = FirestoreService()
    val listSpeakers: MutableLiveData<List<Speaker>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase(){
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result?:emptyList())
                processFinished()
            }
            override fun onFailed(exception: Exception){
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }
}