import * as types from '../actions/types';

const defaultHardwareState = {
  popingAddHardwareDialog: false,
  server: null,
  serverLoading: false,
  serverList: [],
  serverListLoading: false,
};

const hardware = (state = defaultHardwareState, action) => {
  switch (action.type) {
    case types.SERVER_LOAD_BEGIN:
      return Object.assign({}, state, {
        server: null,
        serverLoading: true,
      });
    case types.SERVER_LOAD_SUCCESS:
      return Object.assign({}, state, {
        server: action.info,
        serverLoading: false
      });
    case types.SERVER_LOAD_FAILURE:
      return Object.assign({}, state, {
        server: null,
        serverLoading: false,
      });
    case types.SERVER_LIST_LOAD_BEGIN:
      return Object.assign({}, state, {
        serverList: [],
        server: null,
        serverListLoading: true
      });
    case types.SERVER_LIST_LOAD_SUCCESS:
      return Object.assign({}, state, {
        serverList: action.info.Members.map((each) => {
          return {
            Uri: each.Uri,
            Name: each.Name,
            State: each.State,
            Health: each.Health
          };
        }),
        serverListLoading: false,
      });
    case types.SERVER_LIST_LOAD_FAILURE:
      return Object.assign({}, state, {
        serverList: [],
        server: null,
        serverListLoading: false,
      });
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
        serverList: state.serverList.concat([action.info]),
      });
    default:
      return state;
  }
};

export default hardware;
