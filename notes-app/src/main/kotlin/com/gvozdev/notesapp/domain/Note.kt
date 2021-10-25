package com.gvozdev.notesapp.domain

import java.util.*

data class Note(
    val id: Long,
    val text: String,
    val created: Date = Date(),
    val name: String = "",
    val hashtags: Set<Hashtag> = setOf()
)
