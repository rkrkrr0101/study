import logo from './logo.svg';
import './App.css';
import { Route } from 'react-router-dom';
import PostListPage from './pages/PostListPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import PostPage from './pages/PostPage';
import WritePage from './pages/WritePage';

function App() {
    return (
        <div className="App">
            <Route
                component={PostListPage}
                path={['/@:username', '/']}
                exact
            ></Route>
            <Route component={LoginPage} path={['/login']}></Route>
            <Route component={RegisterPage} path={['/register']}></Route>
            <Route component={PostPage} path={['/@:username/:postId']}></Route>
            <Route component={WritePage} path={['/write']}></Route>
        </div>
    );
}

export default App;
