import { createStore, applyMiddleware  } from 'redux';
import thunkMiddleware from 'redux-thunk';
import rootReducer from './reducer/RootReducer';

export default function configureStore(initialState) {
  const store = createStore(
    rootReducer,
    initialState,
    applyMiddleware(thunkMiddleware));

  return store;
}
