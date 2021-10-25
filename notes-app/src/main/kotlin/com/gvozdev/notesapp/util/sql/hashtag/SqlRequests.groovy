package com.gvozdev.notesapp.util.sql.hashtag

class SqlRequests {
	public static final String ADD_NEW_HASHTAG =
			'''
				INSERT INTO hashtag(id, text)
				VALUES (:id, :text)
			'''

	public static final String DELETE_HASHTAGS_BY_NOTE_ID =
			'''
				DELETE FROM hashtag
				WHERE id IN (SELECT hashtag_id FROM note_hashtag WHERE note_id = :noteId)
			'''

	public static final String FIND_HASHTAGS_BY_NOTE_ID =
			'''
				SELECT hashtag.id, hashtag.text FROM hashtag
				JOIN note_hashtag ON note_hashtag.hashtag_id = hashtag.id
				JOIN note ON note_hashtag.note_id = note.id
				WHERE note.id = :noteId
			'''
}