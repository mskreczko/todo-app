import React from 'react';
import './Footer.css';

class Footer extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <footer>
                <span>Michał Skreczko &copy; 2022</span>
            </footer>
        );
    }
}

export default Footer;