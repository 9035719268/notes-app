package com.gvozdev.notesapp.dao.noteshashtag

import com.gvozdev.notesapp.util.sql.notehashtag.SqlRequests.*
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
open class NoteHashtagDaoImpl(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : NoteHashtagDao {
    override fun addNewNoteHashtagPair(noteId: Long, hashtagId: Long): Int {
        val queryParams = mapOf("noteId" to noteId, "hashtagId" to hashtagId)

        return namedParameterJdbcTemplate.update(ADD_NEW_NOTE_HASHTAG_PAIR, queryParams)
    }

    override fun deleteNoteHashtagPairs(noteId: Long): Int {
        val queryParams = mapOf("noteId" to noteId)

        return namedParameterJdbcTemplate.update(DELETE_NOTE_HASHTAG_PAIRS, queryParams)
    }
}