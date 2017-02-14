import React, { Component } from 'react';
import { connect } from 'react-redux';
import { getAllActivity } from '../actions/ActivityAction';
import Frame from './Frame';

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

    switch (this.props.activity.state) {
      case 'getStart':
        return (<Frame main={<h1>Getting Activities ...</h1>} footer={<p>footer</p>}></Frame>);
        break;
      case 'success':
        let main =
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

        return (<Frame main={main} footer={<p>footer</p>}></Frame>);
        break;
      case 'failure':
        return (<Frame main={<h1>Failed to get activities ...</h1>} footer={<p>footer</p>}></Frame>);
        break;
    }
  }
}

function mapStateToProps(state) {
  const { activity } = state;
  return { activity };
}

export default connect(mapStateToProps)(Activity);