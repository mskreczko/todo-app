import React from 'react';

class TasksList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            tasks: [],
            loaded: false,
        };
    }

    componentDidMount() {
        fetch("http://localhost:8080/api/users/1/tasks")
        .then((res) => res.json())
        .then((tasks) => {this.setState({tasks: tasks, loaded: true})});
    }

    render() {
        const { tasks, loaded } = this.state;

        return (
            <div>
                <h1>Tasks List</h1>

                {tasks.map((task, idx) => (
                    <div key={idx}>
                        <h2>{task.title}</h2>
                        <p>{task.status}</p>
                    </div>
                ))}
            </div>
        );
    }
}

export default TasksList;