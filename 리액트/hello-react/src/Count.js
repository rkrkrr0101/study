import React,{useReducer} from 'react';
function reducer(s,a){
    switch(a.type){
        case 'IN':
            return{value:s.value+1};
        case 'DE':
            return{value:s.value-1};
        default:return s;
    }
}


const Count = () => {
    const [state,dispatch]=useReducer(reducer,{value:0})
    return (
        <div>
            <p>현재 카운터값은 <b>{state.value}입니다</b></p>
            <button onClick={()=>dispatch({type:'IN'})}>+1</button>
            <button onClick={()=>dispatch({type:'DE'})}>-1</button>
        </div>
    );
};

export default Count;