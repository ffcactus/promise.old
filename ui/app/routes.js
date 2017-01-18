import React from 'react';
import { Route, IndexRoute } from 'react-router';
import App from './components/App';
import FilterableTable from './containers/FilterableTable';
import About from './components/About';
import Login from './components/Login';
import AuthenticationContainer from './containers/AuthenticationContainer';
import DashBoard from './components/DashBoard';
import Activity from './components/Activity';
import Hardware from './components/Hardware';

function hasLogin() {
	return localStorage.getItem('login') === 'true';
}

function requireAuth(nextState, replace) {
	if (!hasLogin()) {
		replace('/login');
	}
}
export default (
	<Route path="/" component={App}>
		<IndexRoute onEnter={requireAuth} component={DashBoard} />
		<Route onEnter={requireAuth} path="/table" component={FilterableTable} />
		<Route path="/login" component={Login} />
		<Route onEnter={requireAuth} path="/dashboard" component={DashBoard} />
		<Route onEnter={requireAuth} path="/activity" component={Activity} />
		<Route onEnter={requireAuth} path="/about" component={About} />
		<Route onEnter={requireAuth} path="/hardware" component={Hardware} />
	</Route>
);
