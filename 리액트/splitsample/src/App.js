import React,{ useState,Suspense } from 'react';
import logo from './logo.svg';
import './App.css';
import loadable from '@loadable/component'

const Sp=loadable(()=>import('./SplitMe'),{fallback:<div>loading</div>})


function App() {
  const [visi,setvisi]=useState(false)
  const onclick=()=>{
    setvisi(!visi)
  }
  const onMouseOver=()=>{
    Sp.preload()
  }
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p onClick={onclick} onMouseOver={onMouseOver}>
         abcd
        </p>
        
          {visi&&<Sp></Sp>}
        

          
        
      </header>
    </div>
  );
}

export default App;
