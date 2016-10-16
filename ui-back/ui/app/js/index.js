import React from 'react';
import { createStore } from 'redux';
import { render } from 'react-dom';
import SectionTree from './section';
import SectionAction from './sectionAction';

// require('react');
// require('./section');
//


var divForAll = document.createElement('div');
divForAll.setAttribute("id", "divForAll");
document.body.appendChild(divForAll);

let sectionStore = createStore(SectionAction);

render(
  <SectionTree sectionTree = {sectionStore.getState()} />,
  divForAll
);
