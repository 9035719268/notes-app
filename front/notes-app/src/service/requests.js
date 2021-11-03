import axios from "axios";

export const addNote = () => axios.post("http://localhost:8080/note/add");

export const deleteNote = noteId => axios.delete(`http://localhost:8080/note/${noteId}/delete`);

export const editNote = noteId => axios.patch(`http://localhost:8080/note/${noteId}/edit`);

export const fetchNotes = () => axios.get("http://localhost:8080/notes");

export const findNote = () => axios.get("http://localhost:8080/note/search");