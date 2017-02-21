import * as types from "../actions/types";

const defaultHardwareState = {
  popingAddHardwareDialog: false,
  hardwareList: []
}

const hardware = (state = defaultHardwareState, action) => {
  switch (action.type) {
    case types.HARDWARE_POP_ADD_DIALOG:
      return Object.assign({}, state, {
        popingAddHardwareDialog: true,
      });
    case types.HARDWARE_ADD_DIALOG_CANCEL:
      return Object.assign({}, state, {
        popingAddHardwareDialog: false,
      });
    case types.HARDWARE_ADD_DIALOG_OK:
      return Object.assign({}, state, {
        popingAddHardwareDialog: false,
        hardwareList: state.hardwareList.concat([action.info]),
      });
    default:
      return state;
  }
}

export default hardware;