import store from '../configureStore';

const Promise = require('promise');

function createCoreRequest(method, url, async) {
  let xhr = new XMLHttpRequest();
  if ('withCredentials' in xhr) {
    // Check if the XMLHttpRequest object has a 'withCredentials' property.
    // 'withCredentials' only exists on XMLHTTPRequest2 objects.
    xhr.open(method, url, async);
  } else if (typeof XDomainRequest !== 'undefined') {
    // Otherwise, check if XDomainRequest.
    // XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    xhr = new XDomainRequest();
    xhr.open(method, url, async);
  } else {
    // Otherwise, CORS is not supported by the browser.
    xhr = null;
  }
  return xhr;
}

function doPost(url, request) {
  return new Promise((resolve, reject) => {
    const xhr = createCoreRequest('POST', url, true);
    if (!xhr) {
      throw new Error('CORS not supported');
    }
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.onload = () => {
      resolve(xhr.responseText);
    };
    xhr.onerror = (e) => {
      reject(e);
    };
    xhr.send(JSON.stringify(request));
  });
}

export function login(userName, password) {
  return doPost('http://192.168.116.135/rest/login', {// + store.getState().global.host, {
    userName,
    password,
    domain: 'LOCAL'
  });
}

export { doPost };
