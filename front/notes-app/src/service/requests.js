import axios from "axios";

export const addNote = () => axios.post("http://localhost:8080/note/add");

export const deleteNote = noteId => axios.delete(`http://localhost:8080/note/${noteId}/delete`);

export const editNote = (noteId, noteText, noteName, noteHashtags) => {
  const hashtags = noteHashtags.split(", ").map(hashtag => (
    {id: Math.random() * Date.now(), text: hashtag}
  ));

  return axios.patch(
    `http://localhost:8080/note/${noteId}/edit`,
    {id: noteId, text: noteText, name: noteName, hashtags: hashtags}
  );
};

export const fetchNotes = () => axios.get("http://localhost:8080/notes");

export const findNote = () => axios.get("http://localhost:8080/note/search");