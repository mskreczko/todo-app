import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './TasksList.css';

export default function TasksList() {
    const [tasks, setTasks] = useState();

    useEffect(() => {
        const getTasks = async () => {
            const data = await(
                fetch('http://localhost:8080/api/v1/tasks', {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token'),
                    },
                })
            ).then((response) => response.json());

            setTasks(data);
        };

        getTasks();
    }, []);

    return (
        <ul className='tasks'>
            {tasks && tasks.map((task, idx) => (
                <li className='single-task' key={idx}>
                    <div className='task-preview'>
                        <Link className='single-task-btn' to={`/tasks/${task.id}`}><h2 className='single-task-title'>{task.title}</h2></Link>
                        <Link className='del-icon' to={`/tasks/delete/${task.id}`}><i className='gg-trash'></i></Link>
                    </div>
                </li>
            ))}
        </ul>
    );
}