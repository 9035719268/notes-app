import FetchNotes from "../fetchnotes/FetchNotes";
import "../../service/form-style.css";
import {editNote} from "../../service/requests";

const EditNoteView = () => {
  const handleSubmit = event => {
    event.preventDefault();

    const noteId = event.target[0].value;
    const noteText = event.target[1].value;
    const noteName = event.target[2].value;
    const noteHashtags = event.target[3].value;

    editNote(noteId, noteText, noteName, noteHashtags)
      .then(() => window.location.reload());
  };

  return (
    <div>
      <h1>Редактирование заметки</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-fields">
          <label>
            Введите ID заметки для редактирования:
            <input type="text" placeholder="ID заметки" required />
          </label>
        </div>
        <div className="form-fields">
          <label>
            Введите новый текст заметки:
            <input type="text" placeholder="Текст заметки" required />
          </label>
        </div>
        <div className="form-fields">
          <label>
            Введите новое название заметки (опционально):
            <input id="edit-note-name" type="text" placeholder="Название заметки" />
          </label>
        </div>
        <div className="form-fields">
          <label>
            Введите новые хэштеги для заметки (опционально):
            <input id="edit-note-name" type="text" placeholder="Хэштеги для заметки" />
          </label>
        </div>
        <button type="submit">Подтвердить</button>
      </form>
      <FetchNotes />
    </div>
  );
};

export default EditNoteView;