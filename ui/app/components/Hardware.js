import React, { Component } from 'react';
import { connect } from 'react-redux';
import Frame from './Frame';
import Dialog from './Dialog';
import { popAddHardwareDialog, addHardwareDialogCancel, addHardwareDialogOK } from '../actions/HardwareAction';

class Hardware extends Component {
  constructor(props) {
    super(props);
    this.handleDialog = this.handleDialog.bind(this);
  }

  handleDialog(event) {
    event.preventDefault();
    this.props.dispatch(popAddHardwareDialog());
  }

  render() {
    let addHardwareDialog = <Dialog title="Add Hardware" onCancel={addHardwareDialogCancel} onOK={addHardwareDialogOK} />
    let mainDiv=<div>
      <h1>Hardware</h1>
      <button onClick={this.handleDialog}>Add Hardware</button>
      {(this.props.hardware.popingAddHardwareDialog) ? addHardwareDialog : null}
    </div>
    return (
      <Frame main={mainDiv} footer={<p>footer</p>}>
      </Frame>
    );
  }
}

function mapStateToProps(state) {
  const { hardware } = state;
  return { hardware };
}

export default connect(mapStateToProps)(Hardware);