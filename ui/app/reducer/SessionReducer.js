import * as types from '../actions/types';

const defaultSessionState = {
  state: 'loggout',
  username: null,
  token: null
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

export default session;
