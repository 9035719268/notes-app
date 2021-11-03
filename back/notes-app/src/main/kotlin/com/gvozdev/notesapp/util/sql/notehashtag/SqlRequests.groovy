package com.gvozdev.notesapp.util.sql.notehashtag

class SqlRequests {
	public static final String ADD_NEW_NOTE_HASHTAG_PAIR =
			'''
				INSERT INTO note_hashtag(note_id, hashtag_id)
				VALUES (:noteId, :hashtagId)
			'''

	public static final String DELETE_NOTE_HASHTAG_PAIRS =
			'''
				DELETE FROM note_hashtag
				WHERE note_id = :noteId
			'''
}