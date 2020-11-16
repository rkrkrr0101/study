import React,{useState} from 'react';

const Say = () => {
    const [message,setMessage]=useState('')
    const onClickEnter=()=>setMessage('안녕하세요~')
    const onClickLeave=()=>setMessage('안녕히가세요~')

    const [col,SetColor]=useState('black')

    return (
        <div>
            <button onClick={onClickEnter}>입장</button>
            <button onClick={onClickLeave}>퇴장</button>
            <h1 style={{color:col}}> {message}</h1>
            <button style={{color:'red'}}onClick={()=>SetColor('red')}>빨강</button>
            <button style={{color:'green'}}onClick={()=>SetColor('green')}>녹색</button>
            <button style={{color:'blue'}}onClick={()=>SetColor('blue')}>파랑</button>

        </div>
    );
};

export default Say;

