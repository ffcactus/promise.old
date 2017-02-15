import { routerReducer as routing } from 'react-router-redux';
import { combineReducers } from 'redux';
import * as types from './actions/types';

const filter = (state = '', action) => {
  switch (action.type) {
    case types.FILTER:
      return action.filter;
    default:
      return state;
  }
};

const defaultSessionState = {
  state: 'loggout',
  username: null,
  token: null,
  popDialog: false,
};


const session = (state = defaultSessionState, action) => {
  switch (action.type) {
    case types.LOGIN_REQUEST:
      return {
        state: 'logging',
        username: action.username,
        token: null
      };
    case types.LOGIN_SUCCESS:
      return {
        state: 'logged',
        token: action.token
      }
    case types.POP_DIALOG:
      return {
        popDialog: true
      };
    case types.LOGIN_FAILURE:
      return defaultSessionState;
    case types.LOGOUT_REQUEST:
      return state;
    case types.LOGOUT_SUCCESS:
      return defaultSessionState;
    case types.LOGOUT_FAILURE:
      return state;
    default:
      return state;
  }
};

const defaultActivityState = {
  state: 'getStart',
  value: []
};

const activity = (state = defaultActivityState, action) => {
  switch (action.type) {
    case types.ACTIVITY_GET_START:
      return {
        state: 'getStart',
      };
    case types.ACTIVITY_GET_SUCCESS:
      return {
        state: 'success',
        value: action.info
      };
    case types.ACTIVITY_GET_FAILURE:
      return {
        state: 'failure',
        value: action.info
      };
    default:
      return state;
  }
};

const defaultHardwareState = {
  popingAddHardwareDialog: false
}

const hardware = (state = defaultHardwareState, action) => {
  switch (action.type) {
    case types.HARDWARE_POP_ADD_DIALOG:
      return {
        popingAddHardwareDialog: true,
      };
    case types.HARDWARE_ADD_DIALOG_CANCEL:
      return {
        popingAddHardwareDialog: false,
      };
    case types.HARDWARE_ADD_DIALOG_OK:
      return {
        popingAddHardwareDialog: false,
      }
    default:
      return state;
  }
}

const rootReducer = combineReducers({
  filter,
  session,
  activity,
  hardware,
  routing
});

export default rootReducer;
