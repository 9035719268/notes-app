import "./go-back-button.css";
import React from "react";
import {Link} from "react-router-dom";

const GoBackButton = () => {
  return (
    <Link to="/">
      <button className="go-back-button" type="button">Вернуться в меню</button>
    </Link>
  );
};

export default GoBackButton;