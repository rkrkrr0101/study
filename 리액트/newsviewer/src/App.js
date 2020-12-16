import Axios from 'axios';
import React,{useCallback, useState} from 'react';
import { Route } from 'react-router-dom';
import Categories from './compo/Categories';
import NewsList from './compo/NewsList'
import newsPage from './newsPage';

const App = () => {


  return (
    <Route path="/:category?" component={newsPage}></Route>
  );
};

export default App;
