import * as types from './types';

export function hardwareActionPopAddDialog() {
  return {
    type: types.HARDWARE_POP_ADD_DIALOG,
  }
}

export function hardwareActionDialogCancel() {
  return {
    type: types.HARDWARE_ADD_DIALOG_CANCEL,
  }
}

export function hardwareActionDialogOK() {
  return {
    type: types.HARDWARE_ADD_DIALOG_OK,
  }
}

export function hardwareActionDialogInput(hardware) {
  return {
    type: types.HARDWARE_ADD_INPUT,
    info: hardware
  }
}