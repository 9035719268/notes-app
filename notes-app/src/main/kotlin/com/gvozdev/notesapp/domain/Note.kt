package com.gvozdev.notesapp.domain

import java.util.*

data class Note(
    val id: Long,
    val name: String = "",
    val text: String,
    val creationDate: Date,
    val hashtags: Set<String> = setOf()
)
