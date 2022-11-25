import React from 'react';
import './Tasks.css';
import Task from './Task/Task';

class Tasks extends React.Component {
    constructor(props) {
        super(props);

        this.tasks = [
            {
                id: 1,
                title: 'Walk the dog',
            },
            {
                id: 2,
                title: 'Buy some groceries',
            }
        ];
    }
    render() {
        return (
            <div>
                <h1 className="header">Tasks List</h1>

                {this.tasks.map(task => (
                    <Task
                        id={task.id}
                        title={task.title}
                    />
                ))}
            </div>
        );
    }
}

export default Tasks;