import { React, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginForm.css';

function LoginForm() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const onChange = e => {
        if (e.target.name === 'email') {
            setEmail(e.target.value);
        }
        if (e.target.name === 'password') {
            setPassword(e.target.value);
        }
    }

    const onSubmit = e => {
        e.preventDefault();
        fetch('http://localhost:8080/api/v1/auth/signin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({'email': email, 'password': password}),
        })
        .then((response) => { 
            if (response.status === 200)
                return response.text();
            }).then((data) => { localStorage.setItem("token", data); navigate('/tasks') })
            .catch((error) => { console.log(error) });
    }

    return (
        <form className='login-form' onSubmit={onSubmit}>
            <input className='input-field' type='email' value={email} onChange={onChange} name="email" placeholder='Enter your email'/>
            <input className='input-field' type='password' value={password} onChange={onChange} name="password" placeholder='Enter your password'/>
            <button type='submit' className='submit-form-btn'>Sign in</button>
        </form>
    )
}

export default LoginForm;