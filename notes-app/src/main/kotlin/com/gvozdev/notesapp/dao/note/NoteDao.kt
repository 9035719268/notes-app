package com.gvozdev.notesapp.dao.note

import com.gvozdev.notesapp.domain.Note
import org.springframework.stereotype.Repository

@Repository
interface NoteDao {
    fun addNewNote(note: Note): Int

    fun deleteNoteById(noteId: Long): Int

    fun editNoteById(noteId: Long, noteText: String, noteName: String): Int

    fun findNoteByText(noteText: String): Note?

    fun findNoteByName(noteName: String): Note?

    fun findAllNotes(): List<Note>
}