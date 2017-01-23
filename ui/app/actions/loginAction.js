import * as types from './types';

function loginRequest(username, password) {
  return {
    type: types.LOGIN_REQUEST,
    username
  }
}

function loginFailure(info) {
  return {
    type: types.LOGIN_FAILURE,
    info
  }
}

function loginSuccess(token) {
  return {
    type: types.LOGIN_SUCCESS,
    token
  }
}

/**
 * The async action of login. It will involve sync actions.
 * 
 */
export function login(username, password) {
  return dispatch => {
    dispatch(loginRequest(username, password));
    setTimeout(()=> {
      dispatch(loginSuccess('myToken'));
    }, 5000);
  }
}