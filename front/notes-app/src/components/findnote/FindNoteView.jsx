import FetchNotes from "../fetchnotes/FetchNotes";
import {findNote} from "../../service/requests";
import Note from "../note/Note";
import {useState} from "react";

const FindNoteView = () => {
  const [noteInfo, setNoteInfo] = useState();

  const foundNote = note => {
    return (
      <div>
        <h1>Найденная заметка:</h1>
        <Note note={note} />
      </div>
    );
  };

  const handleSubmit = event => {
    event.preventDefault();

    const searchText = event.target[0].value;

    findNote(searchText)
      .then(response => setNoteInfo(foundNote(response.data)))
      .catch(() => setNoteInfo("Заметка не найдена"));
  };

  return (
    <div>
      <h1>Поиск заметки</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Введите текст или название заметки для поиска:
          <input type="text" placeholder="Введите текст" />
        </label>
        <button>Найти заметку</button>
      </form>
      <p>{noteInfo}</p>
      <FetchNotes />
    </div>
  );
};

export default FindNoteView;