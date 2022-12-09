import { React, useState, setState } from 'react';
// import './LoginForm.css';

function LoginForm() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

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
        fetch('http://localhost:8080/api/auth/signin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({'email': email, 'password': password}),
        })
        .then((response) => { return response.json() })
        .then((json) => { console.log(json) })
        .catch((error) => { console.log(error)} )

        // props.history.push('/tasks'/); <--- does not work
        // after successful login redirect to main page with visible tasks
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