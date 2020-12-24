import React, { useCallback } from 'react';
import Counter from '../compo/Counter';
import {increase,decrease} from '../modules/Counter'
import {useSelector,useDispatch} from 'react-redux' 


const Countercontain = () => {
    const number=useSelector(state=>state.counter.number)
    const dispatch=useDispatch()
    const onIncrease=useCallback(()=>dispatch(increase()),[dispatch])
    const onDecrease=useCallback(()=>dispatch(decrease()),[dispatch])
    return (
        <Counter number={number} onIncrease={onIncrease} onDecrease={onDecrease} ></Counter>
    );
};


export default Countercontain  ;