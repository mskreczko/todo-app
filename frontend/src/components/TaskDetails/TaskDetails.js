import React from 'react';
import { useParams } from 'react-router-dom';
import './TaskDetails.css';

class TaskDetails extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            task: null,
        };
    }

    componentDidMount() {
        const { userId, taskId } = this.props.params;
        fetch("http://localhost:8080/api/users/" + userId + "/tasks/" + taskId)
        .then((res) => res.json())
        .then((task) => {this.setState({task: task})});
    }

    render() {
        if (this.state.task == null) {
            return (<p>Loading</p>);
        }

        const task = this.state.task;

        return (
            <div className='task'>
                <h2>{task.title}</h2>
                <p>{task.description}</p>
                <p>{task.creationDate}</p>
            </div>
        );
    }
}

export default (props) => (
    <TaskDetails
        {...props}
        params={useParams()}
    />
)