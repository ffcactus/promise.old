import React, { PropTypes, Component } from 'react';
import { connect } from 'react-redux';
import Frame from './common/Frame';
// import AddHardwareDialog from '../containers/hardware/AddHardwareDialog';
import ServerListFrame from './Server/ServerListFrame';
import ServerListElement from './Server/ServerListElement';
import ServerDetailFrame from './Server/ServerDetailFrame';
import ServerAppFrame from './Server/ServerAppFrame';

// import { ProgressBar } from '../components/common/ProgressBar';
import * as Action from '../actions/HardwareAction';

class Hardware extends Component {
  constructor(props) {
    super(props);
    this.onServerSelect = this.onServerSelect.bind(this);
    this.onOpenDialog = this.onOpenDialog.bind(this);
    this.onAddHardwareDialogOk = this.onAddHardwareDialogOk.bind(this);
    this.onAddHardwareDialogCancel = this.onAddHardwareDialogCancel.bind(this);
  }

  componentDidMount() {
    if (!this.props.hardware.loaded) {
      this.props.dispatch(Action.loadAllServer('10.93.81.79:8080'));
    }
  }

  onServerSelect(event) {
    event.preventDefault();
    this.props.dispatch(Action.loadServer('10.93.81.79:8080', '1111'));
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
    // const getHardwareDialog = () => {
    //   return <AddHardwareDialog onCancel={this.onAddHardwareDialogCancel} onOk={this.onAddHardwareDialogOk} />;
    // };

    // const getMainContent = () => {
    //   return (
    //     <div>
    //       <h1>Hardware</h1>
    //       <button onClick={this.onOpenDialog}>Add Hardware</button>
    //       <ServerListFrame elements="Server1"/>
    //       <ServerDetailFrame elements="ServerDetail"/>
    //       {/*
    //         this.props.hardware.hardwareList.map((hardware) => {
    //           return (<p key={hardware.Uri}>{hardware.Uri}</p>);
    //         })
    //       */}
    //       {(this.props.hardware.popingAddHardwareDialog) ? getHardwareDialog() : null}
    //     </div>
    //   );
    // };

    const getMainContent = () => {
      return (
        <ServerAppFrame>
          <ServerListFrame>
            {
              this.props.hardware.serverListLoading && <p>Loading</p>
            }
            {
              (!this.props.hardware.serverListLoading) && this.props.hardware.serverList.map((each) => {
                return (<ServerListElement key={each.Uri} serverUri={each.Uri}>{each.Name}</ServerListElement>);
              })
            }
          </ServerListFrame>
          <ServerDetailFrame/>
        </ServerAppFrame>
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
