import { createStore, applyMiddleware  } from "redux";
import thunkMiddleware from "redux-thunk";
import rootReducer from "./reducer/RootReducer"
import DevTools from "./containers/DevTools";

export default function configureStore(initialState) {
    const store = createStore(
        rootReducer,
        initialState,
        applyMiddleware(thunkMiddleware)
        //DevTools.instrument()
    );

    return store;
}
