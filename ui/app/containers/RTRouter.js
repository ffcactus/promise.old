import React, { PropTypes } from 'react';
import { Router, Route, IndexRoute } from 'react-router';
import App from '../components/App';
import About from '../components/About';
import Login from '../components/Login';
import DashBoard from '../components/DashBoard';
import Activity from '../components/Activity';
import Hardware from '../components/Hardware';
import Setting from '../components/Setting';

export default class RTRouter extends React.Component {

  constructor() {
    super();

    this.requireAuth = this.requireAuth.bind(this);
    this.isLoggedIn = this.isLoggedIn.bind(this);

    /**
     * If the user go to any protected routes, he will be redirected to login router
     * if he is not logged in.
     */

    this.routes = (
      <Route path="/" components={App}>
        <IndexRoute onEnter={this.requireAuth} component={DashBoard} />
        <Route path="/login" component={Login} />
        <Route onEnter={this.requireAuth} path="/dashboard" component={DashBoard} />
        <Route onEnter={this.requireAuth} path="/activity" component={Activity} />
        <Route onEnter={this.requireAuth} path="/hardware" component={Hardware} />
        <Route onEnter={this.requireAuth} path="/setting" component={Setting} />
        <Route onEnter={this.requireAuth} path="/about" component={About} />
      </Route>
    );
  }

  isLoggedIn(state) {
    return state.session.state === 'logged';
  }

  /**
   * If the user go to any protected routes, he will be redirected to login router
   * if he is not logged in.
   */
  requireAuth(nextState, replace) {
    const { getState } = this.props;

    // Use my reselect selector to determine if we are authenticated or not
    // Obviously this is not complete, but at this point it's easy to redirect or
    // initiate an action in order to login via cookie or whatever
    if (!this.isLoggedIn(getState())) {
      replace({
        pathname: '/login',
        state: { nextPathname: nextState.location.pathname }
      });
    }
  }

  render() {
    const { history } = this.props;
    return (
      <Router history={history}>
        {this.routes}
      </Router>
    );
  }
}

RTRouter.propTypes = {
  getState: PropTypes.func,
  history: PropTypes.object,
};
