export const ADD_SECTION = "ADD_SECTION";
export const DELETE_SECTION = "DELETE_SECTION";
export const UPDATE_SECTION = "UPDATE_SECTION";
export const SELECT_SECTION = "SELECT_SECTION";
export const GET_SECTION = "GET_SECTION";

export function addSection(name) {
  return {
    type: ADD_SECTION,
    name
  };
}

export function deleteSection(id) {
  return {
    type: DELETE_SECTION,
    id
  };
}

export function updateSection(id, name) {
  return {
    type: UPDATE_SECTION,
    id,
    name
  };
}

export function selectSection(id) {
  return {
    type: SELECT_SECTION,
    id
  };
}

export function getSection(section, section_section) {
  return {
    type: GET_SECTION,
    section,
    section_section
  }
}
