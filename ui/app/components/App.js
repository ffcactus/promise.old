import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import { Link, withRouter } from 'react-router';
import styles from '../styles/header.css'

const App = ({ children }) =>
    <div>
        <header styleName='header'>
            <Link to="/" styleName="item">Dashboard</Link>
            <Link to="/table" styleName="item">Filterable Table</Link>
            <Link to="/about" styleName="item">About</Link>
            <Link to="/login" styleName="item">Login</Link>
            <Link to="/activity" styleName="item">Activity</Link>
            <Link to="/hardware" styleName="item">Hardware</Link>
        </header>
        {children}
    </div>;


App.propTypes = {
    children: PropTypes.object
};

export default CSSModules(App, styles);
