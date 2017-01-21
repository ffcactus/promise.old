import { routerReducer as routing } from 'react-router-redux';
import { combineReducers } from 'redux';
import * as types from './actions/types';

const filter = (state = '', action) => {
    switch (action.type) {
        case types.FILTER:
            return action.filter;
        default:
            return state;
    }
};

const loginReducer = (state = { loggedIn : false }, action) => {
    switch(action.type) {
        case 'login':
            return { loggedIn : true };
        case 'logout':
            return { loggedIn : false };
        default:
            return state;
    }
};

const rootReducer = combineReducers({
    filter,
    loginReducer,
    routing
});

export default rootReducer;
