import {Route,Link,Switch} from 'react-router-dom';
import About from './About';
import './App.css';
import Home from './Home';
import Profiles from './Profiles';
import HistorySam from './HistorySam'

function App() {
  return (
    <div className="App">
    <ul>
      <li>
       <Link to='/'>홈</Link>
      </li>
      <li>
       <Link to='/About'>소개</Link>
      </li>
      <li>
       <Link to='/info'>인포ㅇ</Link>
      </li>   
      <li>
        <Link to='/profiles/'>프로필</Link>
      </li>
      <li>
        <Link to='/history'>히스토리</Link>
      </li>              
    </ul>
    <hr></hr>
    <Switch>
      <Route path='/' component={Home} exact/>
      <Route path={['/About','/info']} component={About}/>
      <Route path='/profiles' component={Profiles}/>
      <Route path='/history' component={HistorySam}/>
      <Route render={({location})=>(
        <div>
          <h2>이페이지는 없습니다</h2>
          <p>{location.pathname}</p>
        </div>
      )}
      />
    </Switch>

    </div>
  );
}

export default App;
