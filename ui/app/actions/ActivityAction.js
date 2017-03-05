import * as types from './types';

const activity = [
  { id: '0001', name: 'Add Hardware', state: 'Running', percentage: 20 },
  { id: '0002', name: 'Add Hardware', state: 'Running', percentage: 20 },
  { id: '0003', name: 'Refresh Hardware', state: 'Running', percentage: 30 },
  { id: '0004', name: 'Refresh Hardware', state: 'Running', percentage: 30 },
  { id: '0005', name: 'Refresh Hardware', state: 'Running', percentage: 50 },
  { id: '0006', name: 'Refresh Hardware', state: 'Running', percentage: 60 }
];

function activityGetStartAction() {
  return {
    type: types.ACTIVITY_GET_START
  };
}

function activityGetSuccessAction() {
  return {
    type: types.ACTIVITY_GET_SUCCESS,
    info: activity
  };
}


export function getAllActivity() {
  return dispatch => {
    dispatch(activityGetStartAction());
    setTimeout(() => {
      dispatch(activityGetSuccessAction());
    }, 3000);
  };
}
