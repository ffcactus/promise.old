import React, { Component } from 'react';
import { connect } from 'react-redux';

let LoginForm = ({ dispatch }) => {
  return (
    <form id="login" onSubmit={(e) => {
      e.preventDefault();
      dispatch({
        type: "login"
      });
    }}>
      <label>Username</label>
      <input id="username" type="text" />
      <label>Password</label>
      <input id="password" type="password" />
      <input type="submit" value="login" />
    </form>
  );
}

export default connect()(LoginForm);