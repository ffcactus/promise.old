import * as types from '../actions/types';

const defaultSettingState = {
  host: null
};

const global = (state = defaultSettingState, action) => {
  switch (action.type) {
    case types.OPEN:
      return Object.assign({}, state, {
        host: action.info.hostAddress
      });
    default:
      return state;
  }
};

export default global;
