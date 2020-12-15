import Axios from 'axios';
import React,{useState} from 'react';

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
    <div>
      <div>
        <button onClick={onClick}>불러오기</button>
      </div>
      {data && <textarea rows={7} value={JSON.stringify(data,null,2)} readOnly={true} ></textarea> }
    </div>
  );
};

export default App;
