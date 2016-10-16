import { ADD_SECTION, DELETE_SECTION, SELECT_SECTION, GET_SECTION } from './sectionAction';

const initialState = {
  selectedSection = null;
  section = [];
  section_section = [];
}

function getSectionReducer(state = initialState, action) {
  switch(action.type) {
    case GET_SECTION:
      return Object.assign({}, state, action.section, action.section_section);
    default:
      return Object.assign({}, state);
  }
}

function mainReducer(state = initialState, action) {
  
}
