import React, { useState, useEffect } from 'react';
import './TasksList.css';

function TasksList() {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
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

            console.log(tasks);
        }

        getTasks();
    }, []);

    return (
        <ul className='tasks'>
            {tasks.map((task, idx) => (
                <li className='single-task' key={idx}>
                    <a className='single-task-btn' href={'/tasks/' + task.id}><h2 className={'single-task-title '+ (task.status==="ACTIVE" ? "active" : "done")}>{task.title}</h2></a>
                </li>
            ))}
        </ul>
    );
}

export default TasksList;