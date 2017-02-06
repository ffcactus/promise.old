import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import { Link, withRouter } from 'react-router';
import Menu from './Menu';
import styles from '../styles/frame.css';


const App = ({ children }) =>
    <div>
        <div styleName="header"><Menu/></div>
        <div styleName="main">{children}</div>
        <div styleName="footer">{children ? children.footer: null}</div>
    </div>;


App.propTypes = {
    children: PropTypes.object
};

export default CSSModules(App, styles);
