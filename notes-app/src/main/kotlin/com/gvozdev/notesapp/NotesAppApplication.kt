package com.gvozdev.notesapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class NotesAppApplication

fun main(args: Array<String>) {
    runApplication<NotesAppApplication>(*args)
}
