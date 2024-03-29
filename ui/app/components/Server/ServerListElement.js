import React, { PropTypes, Component } from 'react';
import { connect } from 'react-redux';
import CSSModules from 'react-css-modules';
import styles from '../../styles/ServerFrame.css';
import * as Action from '../../actions/HardwareAction';

class ServerListElement extends Component {
  constructor(props) {
    super(props);
    this.onSelect = this.onSelect.bind(this);
    this.state = {
      selected: false
    };
  }

  shouldComponentUpdate(nextProps, nextState) {
    return this.state.selected !== nextState.selected;
  }

  onSelect(event) {
    event.preventDefault();
    this.props.dispatch(Action.loadServer('10.93.81.79:8080', this.props.serverUri));
  }

  render() {
    return (
      <div styleName="ServerListElement" onClick={this.onSelect}>{this.props.children}</div>
    );
  }
}

function mapStateToProps(state) {
  const { hardware } = state;
  return { hardware };
}

ServerListElement.propTypes = {
  serverUri: PropTypes.string,
  children: PropTypes.object,
  dispatch: PropTypes.func
};

export default connect(mapStateToProps)(CSSModules(ServerListElement, styles));

