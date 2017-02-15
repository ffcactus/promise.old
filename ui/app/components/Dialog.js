import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import CSSModules from 'react-css-modules';
import styles from '../styles/dialog.css';

class Dialog extends React.Component {

  constructor(props) {
    super(props);
    this.onCancel = this.onCancel.bind(this);
    this.onOK = this.onOK.bind(this);
  }

  onCancel(event) {
    event.preventDefault();
    this.props.dispatch(this.props.onCancel());
  }

  onOK(event) {
    event.preventDefault();
    this.props.dispatch(this.props.onOK());
  }

  render() {
    return (
      <div styleName="popup">
        <h1>{this.props.title}</h1>
        <p>{this.props.content}</p>
        <button onClick={this.onCancel}>Cancel</button >
        <button onClick={this.onOK}>OK</button >
      </div>
    )
  }
}

export default connect()(CSSModules(Dialog, styles));