import './App.css';
import TasksList from './components/TasksList/TasksList';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';

function App() {
  return (
    <div className="App">
      <Header/>
      <TasksList/>
      <Footer/>
    </div>
  );
}

export default App;
