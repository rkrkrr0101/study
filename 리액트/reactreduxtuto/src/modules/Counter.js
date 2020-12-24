import {createAction, handleAction, handleActions} from 'redux-actions'

const INCREASE='counter/INCREASE'
const DECREASE='counter/DECREASE'

export const increase=createAction(INCREASE)
export const decrease=createAction(DECREASE)

const initState={
    number:0
}

const counter=handleActions({
    [INCREASE]:(state,action)=>({number:state.number+1}),
    [DECREASE]:(state,action)=>({number:state.number-1})

},initState)



export default counter
