import React, { Component } from 'react';
import { connect } from 'react-redux';
import {login} from '../actions/loginAction';

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: ''
    };
    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);    
  }

  handleUsernameChange(event) {
    this.setState({ username: event.target.value });
  }

  handlePasswordChange(event) {
    this.setState({ password: event.target.value });
  }

  handleSubmit(event) {
    event.preventDefault();

    // If we can't find a next path after login, we go to root.
    let nextPathname = this.props.location.state ? this.props.location.state.nextPathname : '/';
    this.props.dispatch(login(this.state.username, this.state.password, nextPathname));
  }


  render() {
    return (
      <form id="login" onSubmit={this.handleSubmit}>
        <label>Username</label>
        <input id="username" type="text" onChange={this.handleUsernameChange} />
        <label>Password</label>
        <input id="password" type="password" onChange={this.handlePasswordChange} />
        <input type="submit" value="login" disabled={this.props.session.state === 'logging'} />
      </form>
    );
  }
}

function mapStateToProps(state) {
  const { session } = state;
  return { session };
}

export default connect(mapStateToProps)(Login);