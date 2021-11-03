package com.gvozdev.notesapp.domain

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class HashtagRowMapper : RowMapper<Hashtag> {
    override fun mapRow(resultSet: ResultSet, rowNum: Int): Hashtag {
        val id = resultSet.getLong("id")
        val text = resultSet.getString("text")

        return Hashtag(id, text)
    }
}