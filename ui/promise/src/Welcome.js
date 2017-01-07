import React, { Component } from 'react';
import './Welcome.css';
import LoginTable from './LoginTable';
import TaskList from './TaskList';

class Welcome extends Component {
  render() {
    return (
      <div id="welcome">
        <LoginTable/>
        <TaskList/>
      </div>
    );
  }
}

export default Welcome;
