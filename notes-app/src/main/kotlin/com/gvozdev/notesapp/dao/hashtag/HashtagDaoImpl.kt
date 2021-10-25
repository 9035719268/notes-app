package com.gvozdev.notesapp.dao.hashtag

import com.gvozdev.notesapp.domain.Hashtag
import com.gvozdev.notesapp.util.sql.hashtag.SqlRequests.*
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
open class HashtagDaoImpl(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : HashtagDao {
    override fun addNewHashtag(hashtag: Hashtag): Int {
        val queryParams = mapOf("id" to hashtag.id, "text" to hashtag.text)

        return namedParameterJdbcTemplate.update(ADD_NEW_HASHTAG, queryParams)
    }

    override fun deleteHashtagsByNoteId(noteId: Long): Int {
        val queryParams = mapOf("noteId" to noteId)

        return namedParameterJdbcTemplate.update(DELETE_HASHTAGS_BY_NOTE_ID, queryParams)
    }
}