import React from 'react';

class TestComponent extends React.Component {
    constructor() {
        super();
        this.state = {
            item: "Unknown"
        };
    }

    render() {
        return (
            <div>
                <h1>Shopping List for {this.props.name}</h1>
                <ul>
                    <li>Apple</li>
                    <li>Pear</li>
                    <li>{this.state.item}</li>
                </ul>
            </div>
        );
    }
}

export default TestComponent;
