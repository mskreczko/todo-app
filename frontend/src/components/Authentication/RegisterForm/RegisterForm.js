import { React, useState, setState } from 'react';

// import './RegisterForm.css';

function RegisterForm() {

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
        fetch('http://localhost:8080/api/auth/signup/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({'email': email, 'password': password}),
        })
        // .then((response) => response.json())
        // .then((data) => { console.log(data) })
        .catch((error) => { console.log(error) });

        // props.history.push('/signin'); <--- does not work
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