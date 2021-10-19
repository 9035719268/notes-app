package com.gvozdev.notesapp.restadapter

import org.springframework.web.bind.annotation.*

@RestController
class PageController {

    @GetMapping("/note/create")
    fun createNote(): String {
        return "Create note"
    }

    @DeleteMapping("/note/{id}/delete")
    fun deleteNoteById(@PathVariable id: Int): String {
        return "Deleted note with id: $id"
    }

    @PatchMapping("/note/{id}/edit")
    fun editNoteById(@PathVariable id: Int): String {
        return "Edit note with id: $id"
    }

    @GetMapping("/notes")
    fun getAllNotes(): String {
        return "Get all notes"
    }

    @GetMapping("/note/search")
    fun findNoteByText(@RequestParam text: String): String {
        return "Find notes by text: $text"
    }
}