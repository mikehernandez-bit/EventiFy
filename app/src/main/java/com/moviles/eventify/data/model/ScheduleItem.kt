package com.moviles.eventify.data.model

data class ScheduleItem(
    val hour: String,
    val ampm: String,
    val title: String,
    val speaker: String,
    val speakerDesc: String,
    val place: String,
    val date: String
)
