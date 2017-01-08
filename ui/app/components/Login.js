import React, { Component } from 'react';

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
    alert('A name was submitted: ' + this.state);
    event.preventDefault();
  }

  render() {
    return (
      <form id="login" onSubmit={this.handleSubmit}>
        <label>Username</label>
        <input id="username" type="text" onChange={this.handleUsernameChange} />
        <label>Password</label>
        <input id="password" type="password" onChange={this.handlePasswordChange} />
        <input type="submit" value="login" />
      </form>
    );
  }
}

export default Login;