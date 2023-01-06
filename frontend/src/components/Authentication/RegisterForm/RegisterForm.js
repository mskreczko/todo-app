import { React, useState } from 'react';
import './RegisterForm.css';

async function signInUser(email, password) {
    return await fetch('http://localhost:8080/api/v1/auth/signup/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({'email': email, 'password': password}),
    });
}

// TODO
// render authentication errors
export default function RegisterForm() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const onChange = (e) => {
        if (e.target.name === 'email') {
            setEmail(e.target.value);
        }
        if (e.target.name === 'password') {
            setPassword(e.target.value);
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();
        signInUser(email, password).then((response) => {
            if (response.status === 201) {
                window.location.href = '/signin';
            }
        }).catch(() => {});
    }

    return (
        <section className='register-section'>
            <h1>Sign up</h1>
            <form className='register-form' onSubmit={onSubmit}>
                <input required className='input-field' type='email' value={email} onChange={onChange} name='email' placeholder='Enter your email'/>
                <input required className='input-field' type='password' value={password} onChange={onChange} name='password' placeholder='Enter your password'/>
                <button type='submit' className='submit-form-btn'>Sign up</button>
            </form>
        </section>
    );
}