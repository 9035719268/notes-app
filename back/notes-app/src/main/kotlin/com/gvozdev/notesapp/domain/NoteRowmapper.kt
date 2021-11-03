package com.gvozdev.notesapp.domain

import com.gvozdev.notesapp.util.sql.hashtag.SqlRequests.FIND_HASHTAGS_BY_NOTE_ID
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.ResultSet
import java.util.stream.Collectors.toSet

class NoteRowmapper(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : RowMapper<Note> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): Note {
        val id = resultSet.getLong("id")
        val text = resultSet.getString("text")
        val created = resultSet.getDate("created")
        val name = resultSet.getString("name") ?: ""

        val queryParams = mapOf("noteId" to id)
        val hashtags = namedParameterJdbcTemplate.queryForStream(FIND_HASHTAGS_BY_NOTE_ID, queryParams, HashtagRowMapper())
            .collect(toSet())

        return Note(id, text, created, name, hashtags)
    }
}
