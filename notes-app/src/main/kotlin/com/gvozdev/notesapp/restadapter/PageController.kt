package com.gvozdev.notesapp.restadapter

import com.gvozdev.notesapp.domain.Note
import com.gvozdev.notesapp.service.NoteService
import org.springframework.http.HttpStatus.FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PageController(private val noteService: NoteService) {

    @PostMapping("/note/add")
    fun addNewNote(@RequestBody note: Note): ResponseEntity<String> {
        noteService.addNewNote(note)

        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/note/{id}/delete")
    fun deleteNoteById(@PathVariable id: Long): ResponseEntity<String> {
        noteService.deleteNoteById(id)

        return ResponseEntity.ok().build()

    }

    @PatchMapping("/note/{id}/edit")
    fun editNoteById(@PathVariable id: Long, @RequestBody note: Note): ResponseEntity<String> {
        noteService.editNoteById(id, note.text, note.name, note.hashtags)

        return ResponseEntity.ok().build()
    }

    @GetMapping("/notes")
    fun getAllNotes(): ResponseEntity<List<Note>> {
        return ResponseEntity.ok().body(noteService.getAllNotes())
    }

    @GetMapping("/note/search")
    fun findNoteByText(@RequestParam text: String): ResponseEntity<Note> {
        val note = noteService.findNoteByText(text)

        return if (note != null) {
            ResponseEntity(note, FOUND)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}