import {deleteNote} from "../../service/requests";
import FetchNotes from "../fetchnotes/FetchNotes";
import "./delete-note.css";

const DeleteNoteView = () => {
  const handleSubmit = event => {
    event.preventDefault();

    const noteId = event.target[0].value;

    deleteNote(noteId)
      .then(() => window.location.reload());
  };

  return (
    <div>
      <h1>Удаление заметки</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="delete-note-id">Введите ID заметки для удаления: </label>
        <input id="delete" type="text" placeholder="ID заметки" />
        <button type="submit">Подтвердить</button>
      </form>
      <FetchNotes />
    </div>
  );
};

export default DeleteNoteView;