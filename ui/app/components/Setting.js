import React from "react";
import { connect } from "react-redux";
import Frame from "./common/Frame";
import * as Action from "../actions/SettingAction";

class Setting extends React.Component {
  constructor(props) {
    super(props);
    this.onUploadUpgradeBundleDialogOpen = this.onUploadUpgradeBundleDialogOpen.bind(this);
  }

  onUploadUpgradeBundleDialogOpen(event) {
    event.preventDefault();
    if (!(window.File && window.FileReader && window.FileList && window.Blob)) {
      alert("The File APIs are not fully supported in this browser.");
    } else {
      this.props.dispatch(Action.settingUploadUpgradeBundleAction(document.getElementById("setting_upload_upgrade_bundle_input")));
    }
  }

  render() {

    let file = this.props.setting.upgradeBundle;

    let getMainContent = () => {
      return (
        <div>
          <label>
            Upload upgrade bundle<br />
            <input id="setting_upload_upgrade_bundle_input" type="file" onChange={this.onUploadUpgradeBundleDialogOpen}></input>
          </label>
          <li key="xxx">
            <strong>{file.name + " "}</strong>
            {
              (file.type || 'n/a') + " - " +
              file.size + " bytes, last modified: " +
              (file.lastModifiedDate ? file.lastModifiedDate.toLocaleDateString() : "n/a")
            }
          </li>
        </div>
      );
    }

    return (
      <Frame main={getMainContent()} footer={<p>footer</p>} />
    );
  }
}

function mapStateToProps(state) {
  const { setting } = state;
  return { setting };
}

export default connect(mapStateToProps)(Setting);