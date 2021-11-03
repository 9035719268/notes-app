import Note from "./Note";

export const filterNote = (note, hashtagToSearch, dateToSearch, key) => {
  const filteredByHashtags = note.hashtags.filter(hashtag => hashtag.text.toLowerCase().includes(hashtagToSearch));
  const filteredByDateCreated = note.created === dateToSearch;

  if (filteredByHashtags.length !== 0 || filteredByDateCreated) {
    return (<Note key={key} note={note} />);
  }
};