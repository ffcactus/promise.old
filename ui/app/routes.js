import React from 'react';
import { Route, IndexRoute } from 'react-router';
import App from './components/App';
import FilterableTable from './containers/FilterableTable';
import About from './components/About';
import Login from './components/Login';
import DashBoard from './components/DashBoard';
import Activity from './components/Activity';
import Hardware from './components/Hardware';

export default (
	<Route path="/" component={App}>
		<IndexRoute component={DashBoard} />
		<Route path="/table" component={FilterableTable} />
		<Route path="/login" component={Login} />
		<Route path="/dashboard" component={DashBoard} />
		<Route path="/activity" component={Activity} />
		<Route path="/about" component={About} />
		<Route path="/hardware" component={Hardware} />
	</Route>
);
