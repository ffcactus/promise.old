import * as types from './types';

export function hardwareActionPopAddDialog() {
  return {
    type: types.HARDWARE_POP_ADD_DIALOG
  };
}

export function hardwareActionDialogCancel() {
  return {
    type: types.HARDWARE_ADD_DIALOG_CANCEL
  };
}

export function hardwareActionDialogOk(hardware) {
  return {
    type: types.HARDWARE_ADD_DIALOG_OK,
    info: hardware
  };
}
