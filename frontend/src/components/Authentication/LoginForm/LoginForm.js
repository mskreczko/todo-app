import { React, useState } from 'react';
import { useRecoilState } from 'recoil';
import { authenticationState } from '../../atoms/AuthenticationAtom';
import './LoginForm.css';

async function sendAuthRequest(email, password) {
    return await fetch('http://localhost:8080/api/v1/auth/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({'email': email, 'password': password}),
    });
}

// TODO
// render authentication errors
function LoginForm() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const setAuthenticated = useRecoilState(authenticationState)[1];

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
        sendAuthRequest(email, password).then((response) => {
            if (!response.ok) {
                throw new Error('error');
            }
            return response.text();
        }).then((data) => { 
            localStorage.setItem("token", data);
            setAuthenticated(true);
            window.location.href = '/tasks';
        }).catch(() => {});
    }

    return (
        <section className='login-section'>
            <h1>Sign in</h1>
            <form className='login-form' onSubmit={onSubmit}>
                <input className='input-field' type='email' value={email} onChange={onChange} name="email" placeholder='Enter your email'/>
                <input className='input-field' type='password' value={password} onChange={onChange} name="password" placeholder='Enter your password'/>
                <button type='submit' className='submit-form-btn'>Sign in</button>
            </form>
        </section>
    )
}

export default LoginForm;