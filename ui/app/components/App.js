import React, { PropTypes } from 'react';
import { Link } from 'react-router';
import { footer } from '../styles/footer.scss';
import { loginBackground } from '../styles/loginBackground.scss';

const App = ({ children }) =>
    <div id="loginBackground" className={loginBackground}>
        <header className={footer}>
            <Link to="/">Filterable Table</Link>
            <Link to="/about">About</Link>
            <Link to="/login">Login</Link>
            <Link to="/dashboard">Dash Board</Link>
            <Link to="/activity">Activity</Link>
            <Link to="/hardware">Hardware</Link>
        </header>
        {children}
    </div>;

App.propTypes = {
    children: PropTypes.object
};

export default App;
