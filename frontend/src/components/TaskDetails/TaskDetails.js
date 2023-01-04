import { React, useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './TaskDetails.css';

// TODO
// apply styling
export default function TaskDetails() {
    const [task, setTask] = useState('');
    const taskId = useParams();
    const navigate = useNavigate();

    async function getTaskDetails() {
        await fetch('http://localhost:8080/api/v1/tasks/' + taskId.id, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
        })
        .then((res) => res.json())
        .then((task) => setTask(task));
    }

    getTaskDetails();

    return (
        <div className='task-details'>
            <h2>{task.title}</h2>
            <p>{task.description}</p>
            <p>{task.creationDate}</p>
            <span>{task.status}</span>
        </div>
    );
}