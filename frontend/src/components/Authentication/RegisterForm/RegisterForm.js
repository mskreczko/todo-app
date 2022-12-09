import { React, useState, useEffect } from 'react';
import { Navigate, useNavigate } from 'react-router-dom';
import './RegisterForm.css';

function RegisterForm() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

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
        fetch('http://localhost:8080/api/auth/signup/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({'email': email, 'password': password}),
        })
        .catch((error) => { console.log(error) });

        navigate('/signin');
    }

    return (
        <form className='register-form' onSubmit={onSubmit}>
            <input className='input-field' type='email' value={email} onChange={onChange} name='email' placeholder='Enter your email'/>
            <input className='input-field' type='password' value={password} onChange={onChange} name='password' placeholder='Enter your password'/>
            <button type='submit' className='submit-form-btn'>Sign up</button>
        </form>
    );
}

export default RegisterForm;