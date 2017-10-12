import React, { PropTypes } from 'react';
import CSSModules from 'react-css-modules';
import styles from '../../styles/tab.css';

function Tab(props) {
  function onTabClick(event) {
    let i;
    event.preventDefault();
    const tabContent = document.getElementsByClassName('tabcontent');
    for (i = 0; i < tabContent.length; i++) {
      tabContent[i].style.display = 'none';
    }
    const tabLinks = document.getElementsByClassName('tablinks');
    for (i = 0; i < tabLinks; i++) {
      tabLinks[i].className = tabLinks[i].className.replace(' active', '');
    }
    document.getElementById(event.currentTarget.value).style.display = 'block';
    event.currentTarget.className += ' active';
  }

  return (
    <div className={styles.root}>
      <div className="tab">
        {props.pages.map(each => {
          return (
            <button key={each.title} onClick={onTabClick} value={each.title}>{each.title}</button>
          );
        })}
      </div>
      {props.pages.map(each => {
        return (
          <div id={each.title} key={each.title} className="tabcontent">{each.content}</div>
        );
      })}
    </div>
  );
}

Tab.propTypes = {
  pages: PropTypes.array,
};

export default CSSModules(Tab, styles);
