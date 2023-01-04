import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './TasksList.css';

// TODO
// apply styling
export default function TasksList() {
    const [tasks, setTasks] = useState([]);
    const navigate = useNavigate();

    async function getTasks() {
        await fetch('http://localhost:8080/api/v1/tasks', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
        })
        .then((response) => response.json())
        .then((tasks) => { setTasks(tasks) })
        .catch((error) => {console.log(error) });
    }

    useEffect(() => {
        getTasks();
    })

    return (
        <ul className='tasks'>
            {tasks.map((task, idx) => (
                <li className='single-task' key={idx}>
                    <div className='task-preview'>
                        <a className='single-task-btn' href={'/tasks/' + task.id}><h2 className='single-task-title'>{task.title}</h2></a>
                        <a href={'/delete?id=' + task.id}><i className='gg-check'></i></a>
                    </div>
                </li>
            ))}
        </ul>
    );
}