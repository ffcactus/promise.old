import React, { PropTypes, Component } from 'react';
import { connect } from 'react-redux';
import Frame from './common/Frame';
import AddHardwareDialog from '../containers/hardware/AddHardwareDialog';
import * as Action from '../actions/HardwareAction';

class Hardware extends Component {
  constructor(props) {
    super(props);
    this.onOpenDialog = this.onOpenDialog.bind(this);
    this.onAddHardwareDialogOk = this.onAddHardwareDialogOk.bind(this);
    this.onAddHardwareDialogCancel = this.onAddHardwareDialogCancel.bind(this);
  }

  onOpenDialog(event) {
    event.preventDefault();
    this.props.dispatch(Action.hardwareActionPopAddDialog());
  }

  onAddHardwareDialogOk(input) {
    this.props.dispatch(Action.hardwareActionDialogOk(input));
  }

  onAddHardwareDialogCancel() {
    this.props.dispatch(Action.hardwareActionDialogCancel());
  }

  render() {
    const getHardwareDialog = () => {
      return <AddHardwareDialog onCancel={this.onAddHardwareDialogCancel} onOk={this.onAddHardwareDialogOk} />;
    };

    const getMainContent = () => {
      return (
        <div >
          <h1>Hardware</h1>
          {
            this.props.hardware.hardwareList.map((hardware) => {
              return (<p key={hardware}>{hardware}</p>);
            })
          }
          <button onClick={this.onOpenDialog}>Add Hardware</button>
          {(this.props.hardware.popingAddHardwareDialog) ? getHardwareDialog() : null}
        </div >
      );
    };

    return (
      <Frame main={getMainContent()} footer={<p>footer</p>} />
    );
  }
}

function mapStateToProps(state) {
  const { hardware } = state;
  return { hardware };
}

Hardware.propTypes = {
  hardware: PropTypes.object,
  dispatch: PropTypes.func,
};

export default connect(mapStateToProps)(Hardware);
