import './App.css';
import TasksList from './components/TasksList/TasksList';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import TaskDetails from './components/TaskDetails/TaskDetails';
import LoginForm from './components/Authentication/LoginForm/LoginForm';
import RegisterForm from './components/Authentication/RegisterForm/RegisterForm';
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Header/>
      <Routes>
        <Route path="/" element={<TasksList/>}/>
        <Route path="/:userId/tasks/:taskId" element={<TaskDetails/>}/>
        <Route path="/signin" element={<LoginForm/>}/>
        <Route path="/signup" element={<RegisterForm/>}/>
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
