import React from 'react';
import styles from '../css/default.css';


var SectionTree = React.createClass({

  getDefaultProps: function() {
    return ({
      "defaultOpen": false,
      "checkboxIdPrefix": "section-checkbox-"
    });
  },

  getInitialState: function() {
    return {
      folderOpenRecords: {}
    }
  },

  onFolderClick: function(event) {
    event.preventDefault;
    var id = event.target.id.substring(this.props.checkboxIdPrefix.length);
    var open = this.isFolderOpen(id);
    this.state.folderOpenRecords[id] = !open;
    this.setState({
      folderOpenRecords : this.state.folderOpenRecords
    });
  },

  isFolderOpen(id) {
    if (this.state.folderOpenRecords.hasOwnProperty(id)) {
      return this.state.folderOpenRecords[id];
    } else {
      return this.props.defaultOpen;
    }
  },

  getFolderIcon(id, isOpen) {
    return (
      <input type="checkbox" id={id} onClick={this.onFolderClick} checked={isOpen} />
    );
  },

  makeNode: function(sectionTree) {
    var openFlag = this.isFolderOpen(sectionTree.id)
    var subSection = openFlag ?
      sectionTree.children.map(function(each) {
        return this.makeNode(each);
      }, this) : "";
    var folderIcon = this.getFolderIcon(this.props.checkboxIdPrefix + sectionTree.id, openFlag);


    return (
      <ul key={sectionTree.id}>
        <li>
          {folderIcon}
          <label htmlFor={sectionTree.id}>{sectionTree.name}</label>
          {subSection}
        </li>
      </ul>
    );
  },

  render: function() {
    return (this.makeNode(this.props.sectionTree));
  }
});

export default SectionTree;
