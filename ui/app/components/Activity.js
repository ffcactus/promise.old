import React, { Component } from 'react';
import { connect } from 'react-redux';
import {getAllActivity} from '../actions/ActivityAction';

class Activity extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.dispatch(getAllActivity());
  }

  render() {

    const makeRows = (activities) => {
      return activities.map((each) => {
        return (
          <tr key={each.id}>
            <td>{each.name}</td>
            <td>{each.state}</td>
            <td>{each.percentage}</td>
          </tr>
        );
      });
    }

    if (this.props.activity.state === 'getStart') {
      return (<p>Getting Activities ...</p>);
    }

    switch (this.props.activity.state) {
      case 'getStart':
        return (<p>Getting Activities ...</p>);
        break;
      case 'success':
        return (
          <table>
            <tbody>
              <tr>
                <th>Name</th>
                <th>State</th>
                <th>Percentage</th>
              </tr>
              {makeRows(this.props.activity.value)}
            </tbody>
          </table>
        );
        break;
      case 'failure':
        return (<p>Failed to get activities ...</p>);
        break;        
    }
  }
}

function mapStateToProps(state) {
  const { activity } = state;
  return { activity };
}

export default connect(mapStateToProps)(Activity);