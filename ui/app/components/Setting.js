import React from "react";
import { connect } from "react-redux";
import Frame from "./common/Frame";
import * as Action from "../actions/SettingAction";

class Setting extends React.Component {
  constructor(props) {
    super(props);
    this.onUploadUpgradeBundleDialogOpen = this.onUploadUpgradeBundleDialogOpen.bind(this);
    this.onUploadUpgradeBundleConfirm = this.onUploadUpgradeBundleConfirm.bind(this);
  }

  onUploadUpgradeBundleDialogOpen(event) {
    event.preventDefault();
    if (!(window.File && window.FileReader && window.FileList && window.Blob)) {
      alert("The File APIs are not fully supported in this browser.");
    } else {
      this.props.dispatch(Action.settingUploadUpgradeBundleAction(document.getElementById("setting_upload_upgrade_bundle_input")));
    }
  }

  onUploadUpgradeBundleConfirm(event) {
    event.preventDefault();
    let reader = new FileReader();
    let fd = new FormData();
    let xhr = new XMLHttpRequest();
    // xhr.upload.addEventListener("progress", (e) => {
    //   if(e.lengthComputable) {
    //     let percentage = Math.round((e.loaded * 100) / e.total);
    //     console.info(percentage);
    //   }
    // }, false);
    // xhr.upload.addEventListener("load", (e) => {
    //   console.info("load");
    // });
    xhr.open("POST", "http://192.168.116.132/rest/setting/upgrade/file", true); //asynchronously.
    xhr.onreadystatechange = () => {
      if(xhr.readyState === 4 && xhr.status == 200) {
        alert(xhr.responseText);
      }
    }
    fd.append("file", this.props.setting.upgradeBundle);
    xhr.send(fd);
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
          <ul>
            <li key="xxx">
              <strong>{file.name + " "}</strong>
              {
                (file.type || 'n/a') + " - " +
                file.size + " bytes, last modified: " +
                (file.lastModifiedDate ? file.lastModifiedDate.toLocaleDateString() : "n/a")
              }
            </li>
          </ul>
          <button onClick={this.onUploadUpgradeBundleConfirm}>Upload</button>
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