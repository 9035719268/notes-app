package com.gvozdev.notesapp.dao.noteshashtag

import org.springframework.stereotype.Repository

@Repository
interface NoteHashtagDao {
    fun addNewNoteHashtagPair(noteId: Long, hashtagId: Long): Int

    fun deleteNoteHashtagPairs(noteId: Long): Int
}