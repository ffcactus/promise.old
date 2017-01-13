import React, { PropTypes } from 'react';
import { Link } from 'react-router';
import { footer } from '../styles/footer.scss';
import { loginBackground } from '../styles/loginBackground.scss';

const App = ({ children }) =>
    <div id="loginBackground" className={root}>
        <header className={footer}>
            <Link to="/">Dashboard</Link>
            <Link to="/table">Filterable Table</Link>
            <Link to="/about">About</Link>
            <Link to="/login">Login</Link>
            <Link to="/activity">Activity</Link>
            <Link to="/hardware">Hardware</Link>
        </header>
        {children}
    </div>;

App.propTypes = {
    children: PropTypes.object
};

export default App;
