package com.gvozdev.notesapp.util.sql.note

class SqlRequests {
	public static final String ADD_NEW_NOTE =
			'''
				INSERT INTO note(id, text, created, name)
				VALUES (:id, :text, :created, :name)
			'''

	public static final String DELETE_NOTE_BY_ID =
			'''
				DELETE FROM note
				WHERE id = :id
			'''

	public static final String EDIT_NOTE_BY_ID =
			'''
				UPDATE note
				SET text = :text, name = :name
				WHERE id = :id
			'''

	public static final String FIND_NOTE_BY_TEXT =
			'''
				SELECT * FROM note
				WHERE text = :noteText
			'''

	public static final String FIND_NOTE_BY_NAME =
			'''
				SELECT * FROM note
				WHERE name = :noteName
			'''

	public static final String FIND_ALL_NOTES =
			'''
				SELECT * FROM note
			'''
}