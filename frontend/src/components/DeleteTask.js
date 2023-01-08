import { useEffect } from 'react';
import { useParams } from 'react-router-dom';

export default function DeleteTask() {
    const params = useParams();

    useEffect(() => {
        const deleteTask = async (id) => {
            console.log('fire');
            await fetch(`http://localhost:8080/api/v1/tasks?id=${id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token'),
                },
            })
            .then(window.location.href = '/tasks')
            .catch((err) => { console.log(err); })
        };

        deleteTask(params.id);
    }, []);


}