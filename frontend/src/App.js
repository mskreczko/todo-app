import './App.css';
import TasksList from './components/TasksList/TasksList';
import RegisterForm from './components/Authentication/RegisterForm/RegisterForm';
import LoginForm from './components/Authentication/LoginForm/LoginForm';
import Logout from './components/Authentication/Logout/Logout';
import Hero from './components/Hero/Hero';
import NewTask from './components/NewTask/NewTask';
import TaskDetails from './components/TaskDetails/TaskDetails';
import { Route, Routes } from 'react-router-dom';
import ProtectedRoute from './components/ProtectedRoute/ProtectedRoute';

export default function App() {
  return (
    <div className='App'>
      <Routes>
        {/* public routes */}
        <Route path='/' element={<Hero/>}/>
        <Route path='signin' element={<LoginForm/>}/>
        <Route path='signup' element={<RegisterForm/>}/>

        {/* protected routes */}
        <Route path='tasks' element={<ProtectedRoute/>}>
          <Route index element={<TasksList/>}/>
          <Route path='new' element={<NewTask/>}/>
          <Route path=':id' element={<TaskDetails/>}/>
        </Route>

        <Route path='logout' element={<ProtectedRoute/>}>
          <Route index element={<Logout/>}/>
        </Route>

      </Routes>
    </div>
  )
}