import Axios from 'axios';
import React,{useState} from 'react';
import NewsList from './compo/NewsList'

const App = () => {
  const [data,setData]=useState(null);
  const onClick=async()=>{
    try{
      const response= await Axios.get('http://newsapi.org/v2/top-headlines?country=kr&apiKey=3753f0a28bad4968a8532d43dafc7b33')
      setData(response.data)
    }catch(e){
      console.log(e)
    }
  }
  return (
    <NewsList></NewsList>
  );
};

export default App;
