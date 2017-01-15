import React, { Component, PropTypes } from 'react';
import { Provider } from 'react-redux';
import CSSModules from 'react-css-modules';
import DevTools from './DevTools';
import { Router } from 'react-router';
import routes from '../routes';
import styles from "../styles/main.css";

class Root extends Component {
    render() {
        const { store, history } = this.props;
        return (
            <Provider store={store}>
                <div id="fullscreen" styleName='rootBackground'>
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

export default CSSModules(Root, styles);


