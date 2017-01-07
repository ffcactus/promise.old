import React, { Component } from 'react';

class TaskItem extends Component {
  constructor(props) {
    super(props);
    this.state = { 
      name: 'Task0',
      state: "Running",
      percentage: "50%"
    };
  }

  render() {
    return (
      <div id=''>
        <p>{this.state.name}</p>
        <p>{this.state.state}</p>
        <p>{this.state.percentage}</p>
      </div>
    );
  }
}

export default TaskItem;
