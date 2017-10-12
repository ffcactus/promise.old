import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import styles from '../../styles/serverDetailTable.css';

const ProcessorSection = props =>
  <div className={styles.root}>
    <img src="../../img/icon/Processor.png"/>
  </div>;


ProcessorSection.propTypes = {
  processors: PropTypes.array
};

export default CSSModules(ProcessorSection, styles);
