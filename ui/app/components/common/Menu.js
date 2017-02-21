import React, { PropTypes } from "react";
import CSSModules from "react-css-modules";
import { Link, withRouter } from "react-router";
import styles from "../../styles/menu.css"

const Menu = () =>
  <header styleName="header">
    <Link to="/" styleName="item">Dashboard</Link>    
    <Link to="/activity" styleName="item">Activity</Link>
    <Link to="/hardware" styleName="item">Hardware</Link>
    <Link to="/login" styleName="item">Login</Link>
    <Link to="/setting" styleName="item">Setting</Link>
    <Link to="/about" styleName="item">About</Link>
  </header>
export default CSSModules(Menu, styles);