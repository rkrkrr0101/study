import logo from './logo.svg';
import './App.css';
import Counter from './compo/Counter';
import Todos from './compo/Todos';
import Countercontain from './container/Countercontain';

function App() {
  return (
    <div className="App">
      <Countercontain></Countercontain>

      <hr/>
      <Todos></Todos>
    </div>
  );
}

export default App;
