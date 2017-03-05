import * as types from './types';

export function open(hostAddress) {
  return {
    type: types.OPEN,
    info: { hostAddress }
  };
}
