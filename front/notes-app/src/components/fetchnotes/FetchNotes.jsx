import {useEffect, useState} from "react";
import FetchNotesView from "./FetchNotesView";
import {fetchNotes} from "../../service/requests";

const FetchNotes = () => {
  const [notes, setNotes] = useState([]);

  useEffect(() => {
    fetchNotes()
      .then(response => setNotes(response.data));
  }, []);

  return (
    <FetchNotesView notes={notes} />
  );
};

export default FetchNotes;