import "./main-page.css";

const MainPageView = () => {
  return (
    <div>
      <a href="/note/add">
        <button className="add-note-button">Добавить заметку</button>
      </a>
      <a href="/note/delete">
        <button className="delete-note-button">Удалить заметку</button>
      </a>
      <a href="/note/edit">
        <button className="edit-note-button">Редактировать заметку</button>
      </a>
      <a href="/notes">
        <button className="fetch-notes-button">Посмореть заметки</button>
      </a>
      <a href="/note/search">
        <button className="find-note-button">Найти заметку</button>
      </a>
    </div>
  );
};

export default MainPageView;