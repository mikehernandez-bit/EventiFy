package com.moviles.eventify.view.adapter

import com.moviles.eventify.model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)

}