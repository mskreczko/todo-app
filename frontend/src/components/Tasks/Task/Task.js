import React from 'react';

function Task(props) {
    return (
        <div className="task" id={props.id}>
           <a href="#"><h3>{props.title}</h3></a>
           <button className="deleteBtn">Delete</button>
        </div>
    );
}

export default Task;