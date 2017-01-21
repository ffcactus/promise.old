import React, { Component, PropTypes } from 'react';
import { Provider } from 'react-redux';
import CSSModules from 'react-css-modules';
import DevTools from './DevTools';
import RTRouter from '../RTRouter';
import styles from "../styles/main.css";

class Root extends Component {
    render() {
        const { store, history } = this.props;
        const { getState } = store;

        return (
            <Provider store={store}>
                <div id="fullscreen" styleName='rootBackground'>
                    <RTRouter history={history} getState={getState} />
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


