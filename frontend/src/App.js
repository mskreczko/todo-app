import './App.css';
import TasksList from './components/TasksList/TasksList';
import RegisterForm from './components/Authentication/RegisterForm/RegisterForm';
import LoginForm from './components/Authentication/LoginForm/LoginForm';
import Logout from './components/Authentication/Logout/Logout';
import Hero from './components/Hero/Hero';
import NewTask from './components/NewTask/NewTask';
import TaskDetails from './components/TaskDetails/TaskDetails';
import { Route, Routes } from 'react-router-dom';

export default function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<Hero/>}/>
        <Route path="/tasks" element={<TasksList/>}/>
        <Route path="/signup" element={<RegisterForm/>}/>
        <Route path="/signin" element={<LoginForm/>}/>
        <Route path="/logout" element={<Logout/>}/>
        <Route path="/tasks/new" element={<NewTask/>}/>
        <Route path="/tasks/:id" element={<TaskDetails/>}/>
      </Routes>
    </div>
  );
}