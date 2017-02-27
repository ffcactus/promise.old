import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import styles from '../styles/frame.css';

class App extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div>{this.props.children}</div>
        )
    }
}

App.propTypes = {
    children: PropTypes.object
};

export default CSSModules(App, styles);
