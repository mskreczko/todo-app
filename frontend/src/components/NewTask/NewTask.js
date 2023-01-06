import { React, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './NewTask.css';

async function createTask(title, description) {
    return await fetch('http://localhost:8080/api/v1/tasks/new', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
        },
        body: JSON.stringify({'title': title, 'description': description}),
    });
}

export default function NewTask() {
    const [title, setTitle] = useState('');
    const [description, setDescription]  = useState('');
    const navigate = useNavigate();

    const onChange = (e) => {
        if (e.target.name === 'title') {
            setTitle(e.target.value);
        }
        if (e.target.name === 'description') {
            setDescription(e.target.value);
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();
        createTask(title, description);
        navigate('/tasks');
    }

    return (
        <section id='new-task'>
            <h1>New Task</h1>
            <form id='new-task-form' onSubmit={onSubmit}>
                <input type='text' id='new-task-title' value={title} onChange={onChange} name='title' placeholder='Title'/>
                <textarea id='new-task-description' value={description} onChange={onChange} name='description' placeholder='Description'/>
                <button type='submit' className='submit-form-btn'>Create new task</button>
            </form>
        </section>
    );
}