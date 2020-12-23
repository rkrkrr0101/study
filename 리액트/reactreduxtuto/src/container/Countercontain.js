import React from 'react';
import Counter from '../compo/Counter';
import {increase,decrease} from '../modules/Counter'
import {connect} from 'react-redux' 
import { bindActionCreators } from 'redux';

const Countercontain = ({number,increase,decrease}) => {
    return (
        <Counter number={number} onIncrease={increase} onDecrease={decrease} ></Counter>
    );
};
const mapStateToProps=state=>({
    number:state.counter.number
})
const mapDispatchToProps=dispatch=>({
    increase:()=>{
        dispatch(increase())
    },
    decrease:()=>{
        dispatch(decrease())
    }    
});

export default connect(
    state=>({
        number:state.counter.number,
    }),

        {
            increase,decrease
        },
    
    
)(Countercontain)  ;