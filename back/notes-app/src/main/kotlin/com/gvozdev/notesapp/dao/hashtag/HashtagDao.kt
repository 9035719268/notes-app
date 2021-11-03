package com.gvozdev.notesapp.dao.hashtag

import com.gvozdev.notesapp.domain.Hashtag
import org.springframework.stereotype.Repository

@Repository
interface HashtagDao {
    fun addNewHashtag(hashtag: Hashtag): Int

    fun deleteHashtagsByNoteId(noteId: Long): Int
}