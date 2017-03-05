import * as types from './types';
import { browserHistory } from 'react-router';

function loginRequest(username, password) {
  return {
    type: types.LOGIN_REQUEST,
    username,
    password
  };
}

// function loginFailure(info) {
//   return {
//     type: types.LOGIN_FAILURE,
//     info
//   };
// }

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
    setTimeout(()=> {
      dispatch(loginSuccess('myToken'));
      // TODO
      // Is it good to do redirection in action?
      browserHistory.push(afterLoginPath);
    }, 1000);
  };
}
