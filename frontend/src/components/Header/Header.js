import React from 'react';
import './Header.css';

class Header extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <header className='hero-header'>
                <h1>TodoList</h1>
            </header>
        );
    }
}

export default Header;