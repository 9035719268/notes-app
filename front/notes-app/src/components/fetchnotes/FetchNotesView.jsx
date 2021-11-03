import Note from "./Note";
import {useState} from "react";
import {filterNote} from "./filter";

const FetchNotesView = props => {
  const [hashtagToSearch, setHashtagToSearch] = useState("");
  const [dateToSearch, setDateToSearch] = useState(undefined);

  const notes = props.notes.map((note, key) => {
    if (hashtagToSearch === "" && dateToSearch === undefined) {
      return (<Note key={key} note={note} />);
    } else {
      return filterNote(note, hashtagToSearch, dateToSearch, key);
    }
  });

  return (
    <div>
      <input type="search" placeholder="Поиск по заметкам" onChange={event => setHashtagToSearch(event.target.value)} />
      <input type="date" onChange={event => setDateToSearch(event.target.value)} />
      <h1>{notes}</h1>
    </div>
  );
};

export default FetchNotesView;