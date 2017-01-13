import React, { Component, PropTypes } from 'react';
import { Provider } from 'react-redux';
import DevTools from './DevTools';
import { Router } from 'react-router';
import routes from '../routes';
import root_background from "../styles/main.scss";

export default class Root extends Component {
    render() {
        const { store, history } = this.props;
        return (
            <Provider store={store}>
                <div id="fullscreen" className={root_background}>
                    <Router history={history} routes={routes} />
                    <DevTools />
                </div>
            </Provider>
        );
    }
}

Root.propTypes = {
    store: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired
};
