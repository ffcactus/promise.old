import React, { Component } from "react";
import { connect } from "react-redux";
import CSSModules from "react-css-modules";
import { login } from "../actions/LoginAction";
import Styles from "../styles/login.css"

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
      <div styleName="loginForm">
      <form id="login" onSubmit={this.handleSubmit}>
        <p styleName="loginTitle">Promise</p>
        <section styleName="loginInput">
          <input id="username" type="text" placeholder="username" onChange={this.handleUsernameChange} />
          <input id="password" type="password" placeholder="password" onChange={this.handlePasswordChange} />
        </section>
        <section styleName="loginSubmit">
          <input type="submit" value="login" disabled={this.props.session.state === 'logging'} />
        </section>
      </form>
      </div>
    );
  }
}

function mapStateToProps(state) {
  const { session } = state;
  return { session };
}

export default connect(mapStateToProps)(CSSModules(Login, Styles));