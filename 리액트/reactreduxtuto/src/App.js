import logo from './logo.svg';
import './App.css';
import Counter from './compo/Counter';
import Todos from './compo/Todos';
import Countercontain from './container/Countercontain';
import TodosContain from './container/TodosContain';

function App() {
  return (
    <div className="App">
      <Countercontain></Countercontain>

      <hr/>
      <TodosContain></TodosContain>
    </div>
  );
}

export default App;
