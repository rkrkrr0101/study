import React,{useState} from 'react';

const FunEvent = () => {
    const [Form,setForm]= useState({
        username:'',
        message:''
    })
    const{username,message}=Form
    const onchange=e=>{
        const nextForm={
            ...Form,
            [e.target.name]:e.target.value
            
        }
        setForm(nextForm)
    }
    const onCLick=()=>{
        alert(username+':'+message)
        setForm({
            username:'',
            message:''
        })
    }
    const onkeypress=e=>{
        if (e.key==='Enter'){
            onCLick();
        }
    }




    return (
        <div>
            <input
                type="text"
                name='username'
                placeholder='abcd'
                value={username}
                onChange={onchange}
                />
                            <input
                type="text"
                name='message'
                placeholder='롤롤롤로'
                value={message}
                onChange={onchange}
                onKeyPress={onkeypress}
                />

        </div>
    );
};

export default FunEvent;