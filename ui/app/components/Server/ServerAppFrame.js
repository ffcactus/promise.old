import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import styles from '../../styles/ServerFrame.css';

function ServerAppFrame(props) {
  return (
    <div styleName="ServerAppFrame">{props.children}</div>
  );
}

ServerAppFrame.propTypes = {
  children: PropTypes.array,
};

export default CSSModules(ServerAppFrame, styles);
