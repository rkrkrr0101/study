import React from 'react';
import {connect} from 'react-redux'
import { increaseAsync,decreaseAsync } from '../module/counter';
import Counter from '../compo/Counter'

const CounterContainer = ({number,increaseAsync,decreaseAsync}) => {
    return (
        <Counter number={number} onIncrease={increaseAsync} onDecrease={decreaseAsync}></Counter>
    );
};

export default connect(
    state=>({
        number:state.counter
    }),
    {
        increaseAsync,
        decreaseAsync
    }
)(CounterContainer);