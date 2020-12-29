import logo from './logo.svg';
import './App.css';
import Menu from './compo/menu'
import RedPage from './pages/RedPage'
import BluePage from './pages/BluePage'
import { Route } from 'react-router-dom';
function App() {
  return (
    <div className="App">
        <Menu></Menu>
        <hr/>
        <Route path='/red' component={RedPage}></Route>
        <Route path='/blue' component={BluePage}></Route>

    </div>
  );
}

export default App;
