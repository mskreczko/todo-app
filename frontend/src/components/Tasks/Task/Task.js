import {React, useState} from 'react';

function Task(props) {
    const [task] = useState();
    const getTask = () => {
        fetch("http://localhost:8080/api/users/1/tasks/" + props.id)
        .then((response) => console.log(response.json()))
    };

    return (
        <div className="task" id={props.id}>
           <a onClick={getTask}><h3>{props.title}</h3></a>
           <button className="deleteBtn">Delete</button>
        </div>
    );
}

export default Task;