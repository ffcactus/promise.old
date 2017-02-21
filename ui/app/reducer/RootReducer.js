import { routerReducer as routing } from 'react-router-redux';
import { combineReducers } from 'redux';
import * as types from "../actions/types";
import session from "./SessionReducer";
import activity from "./ActivityReducer";
import hardware from "./HardwareReducer";

const rootReducer = combineReducers({
  session,
  activity,
  hardware,
  routing
});

export default rootReducer;
