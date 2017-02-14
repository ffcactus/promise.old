import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import styles from '../styles/dialog.css';

function Dialog(props) {
  return (
    <div styleName="popup">
      <h1>Title</h1>
      <p>Content</p>
    </div>
  )
}

export default CSSModules(Dialog, styles);