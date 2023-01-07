import { React, useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './TaskDetails.css';

export default function TaskDetails() {
    const [task, setTask] = useState('');
    const taskId = useParams();

    useEffect(() => {
        const getTaskDetails = async (id) => {
            const data = await(
                fetch('http://localhost:8080/api/v1/tasks/' + id, {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token'),
                    },
                })
            ).then((response) => response.json())

            setTask(data);
        }

        getTaskDetails(taskId.id);
    }, []);

    return (
        <div className='task-details'>
            <h2 className='task-element'>{task.title}</h2>
            <p className='task-element'>{task.creationDate}</p>
            <p id='description' className='task-element'>{task.description}</p>
        </div>
    );
}