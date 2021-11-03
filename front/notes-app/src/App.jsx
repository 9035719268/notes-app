import React from "react";
import {Route, Switch} from "react-router-dom";
import MainPageView from "./components/mainpage/MainPageView";
import AddNoteView from "./components/addnote/AddNoteView";
import DeleteNoteView from "./components/deletenote/DeleteNoteView";
import EditNoteView from "./components/editnote/EditNoteView";
import FindNoteView from "./components/findnote/FindNoteView";
import FetchNotes from "./components/fetchnotes/FetchNotes";

const App = () => {
  return (
    <Switch>
      <Route path="/" component={MainPageView} exact />
      <Route path="/note/add" component={AddNoteView} exact />
      <Route path="/note/delete" component={DeleteNoteView} exact />
      <Route path="/note/edit" component={EditNoteView} exact />
      <Route path="/notes" component={FetchNotes} exact />
      <Route path="/note/search" component={FindNoteView} exact />
    </Switch>
  );
};

export default App;
