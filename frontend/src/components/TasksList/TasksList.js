import React from 'react';
import './TasksList.css';

class TasksList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            tasks: [],
        };
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/users/1/tasks")
        .then((res) => res.json())
        .then((tasks) => {this.setState({tasks: tasks})});
    }

    render() {
        const tasks = this.state.tasks;
        const userId = 1;

        return (
            <ul className='tasks'>
                {tasks.map((task, idx) => (
                    <li className='single-task' key={idx}>
                        <a className='single-task-btn' href={userId + '/tasks/' + task.id}><h2 className={'single-task-title '+ (task.status==="ACTIVE" ? "active" : "done")}>{task.title}</h2></a>
                    </li>
                ))}
            </ul>
        );
    }
}

export default TasksList;