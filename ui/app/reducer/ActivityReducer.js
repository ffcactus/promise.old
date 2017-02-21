import * as types from "../actions/types";

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

export default activity;