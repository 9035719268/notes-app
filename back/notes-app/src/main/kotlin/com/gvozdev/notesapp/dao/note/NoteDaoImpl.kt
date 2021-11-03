package com.gvozdev.notesapp.dao.note

import com.gvozdev.notesapp.domain.Note
import com.gvozdev.notesapp.domain.NoteRowmapper
import com.gvozdev.notesapp.util.sql.note.SqlRequests.*
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
open class NoteDaoImpl(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : NoteDao {
    override fun addNewNote(note: Note): Int {
        val queryParams = mapOf("id" to note.id, "text" to note.text, "created" to note.created, "name" to note.name)

        return namedParameterJdbcTemplate.update(ADD_NEW_NOTE, queryParams)
    }

    override fun deleteNoteById(noteId: Long): Int {
        val queryParams = mapOf("id" to noteId)

        return namedParameterJdbcTemplate.update(DELETE_NOTE_BY_ID, queryParams)
    }

    override fun editNoteById(noteId: Long, noteText: String, noteName: String): Int {
        val queryParams = mapOf("id" to noteId, "text" to noteText, "name" to noteName)

        return namedParameterJdbcTemplate.update(EDIT_NOTE_BY_ID, queryParams)
    }

    override fun findNoteByText(noteText: String): Note? {
        val queryParams = mapOf("noteText" to noteText)

        return namedParameterJdbcTemplate.queryForStream(FIND_NOTE_BY_TEXT, queryParams, NoteRowmapper(namedParameterJdbcTemplate))
            .findFirst()
            .orElse(null)
    }

    override fun findNoteByName(noteName: String): Note? {
        val queryParams = mapOf("noteName" to noteName)

        return namedParameterJdbcTemplate.queryForStream(FIND_NOTE_BY_NAME, queryParams, NoteRowmapper(namedParameterJdbcTemplate))
            .findFirst()
            .orElse(null)
    }

    override fun findAllNotes(): List<Note> {
        return namedParameterJdbcTemplate.query(FIND_ALL_NOTES, NoteRowmapper(namedParameterJdbcTemplate))
    }
}