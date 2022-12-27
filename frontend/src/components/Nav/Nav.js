import React from 'react';
import './Nav.css';

export default function Nav() {
    render(
        <nav>
            <h1>TodoList</h1>
            <a href='/logout'>Logout</a>
        </nav>
    );
}