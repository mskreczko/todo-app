import { React, useState } from 'react';
import { useParams } from 'react-router-dom';
import './TaskDetails.css';

async function getTaskDetails(id) {
    return await fetch('http://localhost:8080/api/v1/tasks/' + id, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
        },
    });
}

// TODO
// apply styling
export default function TaskDetails() {
    const [task, setTask] = useState('');
    const taskId = useParams();

    function getTaskDetails() {
        getTaskDetails(taskId.id)
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