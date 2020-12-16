import Axios from 'axios';
import React,{useCallback, useState} from 'react';
import Categories from './compo/Categories';
import NewsList from './compo/NewsList'

const App = () => {
  const [category,setCategory]=useState('all');
  const onSelect=useCallback(category=>setCategory(category),[])

  return (
    <div>
      <Categories category={category} onSelect={onSelect}></Categories>
      <NewsList category={category}></NewsList>
    </div>
  );
};

export default App;
