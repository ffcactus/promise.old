import React, { Component } from 'react';
import TaskItem from './TaskItem';

class TaskList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      taskList: [<TaskItem key='1' />, <TaskItem key='2' />],
    };
    this.handleAddTask = this.handleAddTask.bind(this);
  }

  handleAddTask() {
    this.setState((preState, props) => {
      let taskList = preState.taskList;
      taskList.push(<TaskItem key='4'/>);
      return({
        taskList
      })
    })
  }

  render() {
    return (
      <div>
        <p>Task List</p>
        {this.state.taskList}
        <input type="button" value="Add Task" onClick={this.handleAddTask} />
      </div>
    );
  }
}

export default TaskList;
