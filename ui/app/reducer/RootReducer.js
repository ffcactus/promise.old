import { routerReducer as routing } from 'react-router-redux';
import { combineReducers } from 'redux';
import global from './GlobalReducer';
import { session } from './SessionReducer';
import activity from './ActivityReducer';
import hardware from './HardwareReducer';
import setting from './SettingReducer';

const rootReducer = combineReducers({
  global,
  session,
  activity,
  hardware,
  setting,
  routing
});

export default rootReducer;
