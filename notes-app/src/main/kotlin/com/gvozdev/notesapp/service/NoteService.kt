package com.gvozdev.notesapp.service

import com.gvozdev.notesapp.domain.Hashtag
import com.gvozdev.notesapp.domain.Note

interface NoteService {
    fun addNewNote(note: Note)

    fun deleteNoteById(noteId: Long)

    fun editNoteById(noteId: Long, noteText: String, noteName: String, hashtags: Set<Hashtag>)

    fun getAllNotes(): List<Note>

    fun findNoteByText(text: String): Note?
}