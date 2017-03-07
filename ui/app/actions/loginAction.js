import * as types from './types';
import { browserHistory } from 'react-router';
import * as Rest from '../utils/Rest';

function loginRequest(username, password) {
  return {
    type: types.LOGIN_REQUEST,
    username,
    password
  };
}

function loginFailure(info) {
  return {
    type: types.LOGIN_FAILURE,
    info
  };
}

function loginSuccess(token) {
  return {
    type: types.LOGIN_SUCCESS,
    token
  };
}

/**
 * The async action of login. It will involve sync actions.
 *
 */
export function login(username, password, afterLoginPath) {
  return dispatch => {
    dispatch(loginRequest(username, password));
    Rest.login(username, password).then((response) => {
      if (response.status === 200) {
        dispatch(loginSuccess(response.response.token));
        // TODO
        // Is it good to do redirection in action?
        browserHistory.push(afterLoginPath);
      } else {
        dispatch(loginFailure(response.response));
      }
    });
  };
}
