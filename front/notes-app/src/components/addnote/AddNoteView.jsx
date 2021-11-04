import FetchNotes from "../fetchnotes/FetchNotes";
import {addNote} from "../../service/requests";
import "../../service/form-style.css";

const AddNoteView = () => {
  const handleSubmit = event => {
    event.preventDefault();

    const noteText = event.target[0].value;
    const noteName = event.target[1].value;
    const noteHashtags = event.target[2].value;

    addNote(noteText, noteName, noteHashtags)
      .then(() => window.location.reload());
  };

  return (
    <div>
      <h1>Добавление заметки</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-fields">
          <label>
            Введите текст заметки:
            <input type="text" placeholder="Текст заметки" required />
          </label>
        </div>
        <div className="form-fields">
          <label>
            Введите название заметки (опционально):
            <input type="text" placeholder="Название заметки" />
          </label>
        </div>
        <div className="form-fields">
          <label>
            Введите хэштеги для заметки (опционально):
            <input type="text" placeholder="Хэштеги для заметки" />
          </label>
        </div>
        <button type="submit">Подтвердить</button>
      </form>
      <FetchNotes />
    </div>
  );
};

export default AddNoteView;