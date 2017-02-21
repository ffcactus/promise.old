import React, { Component } from "react";
import Frame from "./common/Frame";

class DashBoard extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Frame main={<h1>Dashboard</h1>} footer={<p>footer</p>}></Frame>
    );
  }
}

export default DashBoard;