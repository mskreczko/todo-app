import './App.css';
import TasksList from './components/TasksList/TasksList';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import RegisterForm from './components/Authentication/RegisterForm/RegisterForm';
import LoginForm from './components/Authentication/LoginForm/LoginForm';
import Logout from './components/Authentication/Logout/Logout';
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Header/>
      <Routes>
        <Route path="/tasks" element={<TasksList/>}/>
        <Route path="/signup" element={<RegisterForm/>}/>
        <Route path="/signin" element={<LoginForm/>}/>
        <Route path="/logout" element={<Logout/>}/>
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
