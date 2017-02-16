import React, { Component } from 'react';
import { connect } from 'react-redux';
import Frame from './Frame';
import Dialog from './Dialog';
import { hardwareActionPopAddDialog, hardwareActionDialogCancel, hardwareActionDialogOK, hardwareActionDialogInput } from '../actions/HardwareAction';

class Hardware extends Component {
  constructor(props) {
    super(props);
    this.handleDialog = this.handleDialog.bind(this);
    this.handleDialogInput = this.handleDialogInput.bind(this);
  }

  handleDialog(event) {
    event.preventDefault();
    this.props.dispatch(hardwareActionPopAddDialog());
  }

  handleDialogInput(event) {
    event.preventDefault();
    this.props.dispatch(hardwareActionDialogInput(event.target.value));
  }

  render() {
    let dialogMain = <input type='text' onChange={this.handleDialogInput} />
    let addHardwareDialog = <Dialog title="Add Hardware" content={dialogMain} onCancel={hardwareActionDialogCancel} onOK={hardwareActionDialogOK} />
    let mainDiv=<div>
      <h1>Hardware</h1>
      {this.props.hardware.hardwareList.map((hardware) => {
        return(<p>{hardware}</p>)
      })}
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