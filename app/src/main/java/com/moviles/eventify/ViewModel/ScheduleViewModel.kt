package com.moviles.eventify.ViewModel

import androidx.lifecycle.MutableLiveData
import com.moviles.eventify.model.Conference
import com.moviles.eventify.network.Callback
import com.moviles.eventify.network.FirestoreService

class ScheduleViewModel {
    val firestoreService = FirestoreService()
    val listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object: Callback<List<Conference>>{
            override fun onSuccess(result: List<Conference>?){
                listSchedule.postValue(result?:emptyList())
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }
    fun processFinished(){
        isLoading.value=true
    }

}