import './App.css';
import TasksList from './components/TasksList/TasksList';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import TaskDetails from './components/TaskDetails/TaskDetails';
import RegisterForm from './components/Authentication/RegisterForm/RegisterForm';
import LoginForm from './components/Authentication/LoginForm/LoginForm';
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Header/>
      <Routes>
        <Route path="/tasks" element={<TasksList/>}/>
        <Route path="/signup" element={<RegisterForm/>}/>
        <Route path="/signin" element={<LoginForm/>}/>
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
