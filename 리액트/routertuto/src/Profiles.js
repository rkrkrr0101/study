import React from 'react';
import {NavLink,Route} from 'react-router-dom'
import Profile from './Profile'



const Profiles = () => {
    const activeStyle={
        background:'black',
        color:'white'
    }
    return (
        <div>
            
            <h3>목록</h3>
            <ul>
                <li>
                    <NavLink to='/profiles/rkrk' activeStyle={activeStyle}>rkrk</NavLink>
                </li>
                <li>
                    <NavLink to='/profiles/qwer' activeStyle={activeStyle}>qwer</NavLink>
                </li> 
            </ul>
            
            <Route
               path="/profiles"
               exact
               render={()=><div>사용자를 선택해주세요</div>    }
               />
               <Route path="/profiles/:username" component={Profile}></Route>
        </div>
               
    );
};

export default Profiles;