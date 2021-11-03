package com.gvozdev.notesapp.service

import com.gvozdev.notesapp.dao.hashtag.HashtagDao
import com.gvozdev.notesapp.dao.note.NoteDao
import com.gvozdev.notesapp.dao.noteshashtag.NoteHashtagDao
import com.gvozdev.notesapp.domain.Hashtag
import com.gvozdev.notesapp.domain.Note
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class NoteServiceImpl(
    private val noteDao: NoteDao,
    private val hashtagDao: HashtagDao,
    private val noteHashtagDao: NoteHashtagDao
) : NoteService {

    @Transactional
    override fun addNewNote(note: Note) {
        noteDao.addNewNote(note)

        note.hashtags.forEach {
            hashtagDao.addNewHashtag(it)
            noteHashtagDao.addNewNoteHashtagPair(note.id, it.id)
        }
    }

    @Transactional
    override fun deleteNoteById(noteId: Long) {
        noteDao.deleteNoteById(noteId)

        hashtagDao.deleteHashtagsByNoteId(noteId)

        noteHashtagDao.deleteNoteHashtagPairs(noteId)
    }

    @Transactional
    override fun editNoteById(noteId: Long, noteText: String, noteName: String, hashtags: Set<Hashtag>) {
        hashtagDao.deleteHashtagsByNoteId(noteId)

        noteHashtagDao.deleteNoteHashtagPairs(noteId)

        noteDao.editNoteById(noteId, noteText, noteName)

        hashtags.forEach {
            hashtagDao.addNewHashtag(it)
            noteHashtagDao.addNewNoteHashtagPair(noteId, it.id)
        }
    }

    @Transactional
    override fun getAllNotes(): List<Note> {
        return noteDao.findAllNotes()
    }

    @Transactional
    override fun findNoteByText(text: String): Note? {
        return noteDao.findNoteByName(text) ?: noteDao.findNoteByText(text)
    }
}