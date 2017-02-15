import * as types from './types';

export function popAddHardwareDialog() {
  return {
    type: types.HARDWARE_POP_ADD_DIALOG,
  }
}

export function addHardwareDialogCancel() {
  return {
    type: types.HARDWARE_ADD_DIALOG_CANCEL,
  }
}

export function addHardwareDialogOK() {
  return {
    type: types.HARDWARE_ADD_DIALOG_OK,
  }
}