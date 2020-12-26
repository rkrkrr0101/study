import React from 'react'
import './App.css';
import CounterContainer from './container/CounterContainer';
import SampleContainer from './container/SampleContainer';

function App() {
  return (
    <div className="App">
      <CounterContainer></CounterContainer>
      <SampleContainer></SampleContainer>
    </div>
  );
}

export default App;
