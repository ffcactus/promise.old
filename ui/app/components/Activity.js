import React, { Component } from 'react';

const activity = [
  { id: '0001', name: 'Add Hardware', state: 'Running', percentage: 20 },
  { id: '0002', name: 'Add Hardware', state: 'Running', percentage: 20 },
  { id: '0003', name: 'Refresh Hardware', state: 'Running', percentage: 30 },
  { id: '0004', name: 'Refresh Hardware', state: 'Running', percentage: 30 },
  { id: '0005', name: 'Refresh Hardware', state: 'Running', percentage: 50 },
  { id: '0006', name: 'Refresh Hardware', state: 'Running', percentage: 60 }
];

class Activity extends Component {
  constructor(props) {
    super(props);
  }

  render() {

    const rows = activity.map((each) => {
      return (
        <tr key={each.id}>
          <td>{each.name}</td>
          <td>{each.state}</td>
          <td>{each.percentage}</td>
        </tr>
      );
    });
    return (
      <table>
        <tbody>
          <tr>
            <th>Name</th>
            <th>State</th>
            <th>Percentage</th>
          </tr>
          {rows}
        </tbody>
      </table>
    );
  }
}

export default Activity;