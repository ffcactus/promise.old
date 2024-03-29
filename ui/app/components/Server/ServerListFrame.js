import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import styles from '../../styles/ServerFrame.css';

function ServerListFrame(props) {
  return (
    <div styleName="ServerListFrame">{props.children}</div>
  );
}

ServerListFrame.propTypes = {
  children: PropTypes.array,
};

export default CSSModules(ServerListFrame, styles);
