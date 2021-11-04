import axios from "axios";

export const addNote = (noteText, noteName, noteHashtags) => {
  const hashtags = splitHashtags(noteHashtags);

  return axios.post(
    "http://localhost:8080/note/add",
    {id: getRandomId(), text: noteText, name: noteName, hashtags: hashtags}
  );
};

export const deleteNote = noteId => axios.delete(`http://localhost:8080/note/${noteId}/delete`);

export const editNote = (noteId, noteText, noteName, noteHashtags) => {
  const hashtags = splitHashtags(noteHashtags);

  return axios.patch(
    `http://localhost:8080/note/${noteId}/edit`,
    {id: noteId, text: noteText, name: noteName, hashtags: hashtags}
  );
};

export const fetchNotes = () => axios.get("http://localhost:8080/notes");

export const findNote = () => axios.get("http://localhost:8080/note/search");

const splitHashtags = hashtags => {
  return hashtags.split(", ").map(hashtag => (
    {id: getRandomId(), text: hashtag}
  ));
};

const getRandomId = () => Math.random() * Date.now();