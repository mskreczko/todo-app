import React from 'react';
import './Hero.css';

export default function Hero() {
    return (
        <section className='hero'>
            <div className='header'>
                <h2 id='hero-header'>TodoList</h2>
            </div>
            <div className='hero-footer'>
                <a className='sign-btn' href='/signin'>SIGN IN</a>
                <a className='sign-btn' href='/signup'>SIGN UP</a>
            </div>
        </section>
    );
}