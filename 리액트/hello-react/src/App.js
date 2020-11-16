import React,{useState} from 'react';
import Info from './Info'
const App = () => {
  const [state, setstate] = useState('initialState')
  setstate('a')
  return (
    <div>
      <Info></Info>
    </div>
  );
};

export default App;