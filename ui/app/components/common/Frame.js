import React, { PropTypes } from "react";
import CSSModules from "react-css-modules";
import { Link, withRouter } from "react-router";
import styles from "../../styles/frame.css";
import Menu from "./Menu";

function Frame(props) {
  return (
    <div>
      <div styleName="header"><Menu /></div>
      <div styleName="main">{props.main}</div>
      <div styleName="footer">{props.footer}</div>
    </div>
  )
}

Frame.propTypes = {
  children: PropTypes.object
};

export default CSSModules(Frame, styles);