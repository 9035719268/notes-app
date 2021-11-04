import "./note-style.css";

const Note = props => {
  const id = props.note.id;
  const text = props.note.text;
  const created = props.note.created;
  const hashtags = props.note.hashtags;

  let noteName = "";
  if (props.note.name !== "") {
    noteName = "Название заметки: " + props.note.name;

  }

  let noteHashtags = "";
  if (hashtags.length !== 0 && hashtags[0].text !== "") {
    noteHashtags = "Хэштеги: ";
    hashtags.forEach(hashtag => {
      noteHashtags += hashtag.text;
      noteHashtags += " ";
    });
  }

  return (
    <div className="note">
      <div className="container">
        <p><i>ID: {id}</i></p>
        <p>Текст заметки: {text}</p>
        <p>Дата создания: {created}</p>
        <p>{noteName}</p>
        <p>{noteHashtags}</p>
      </div>
    </div>
  );
};

export default Note;