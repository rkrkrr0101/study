import {createAction,handleActions} from 'redux-actions'
import{delay,put,takeEvery,takeLatest, throttle} from 'redux-saga/effects'

const INCREASE='counter/INCREASE'
const DECREASE='counter/DECREASE'
const INCREASE_ASYNC='counter/INCREASE_ASYNC'
const DECREASE_ASYNC='counter/DECREASE_ASYNC' 

export const increase=createAction(INCREASE)
export const decrease=createAction(DECREASE)

export const increaseAsync=createAction(INCREASE_ASYNC,()=>undefined)
export const decreaseAsync=createAction(DECREASE_ASYNC,()=>undefined)

function* increaseSaga(){
    yield delay(1000)
    yield put(increase())
}
function* decreaseSaga(){
    yield delay(1000)
    yield put(decrease())
}

export function* counterSaga(){
    yield throttle(3000,INCREASE_ASYNC,increaseSaga)
    yield takeLatest(DECREASE_ASYNC,decreaseSaga)
}

const initstate=0

const counter = handleActions({
    [INCREASE]:state=>state+1,
    [DECREASE]:state=>state-1
},initstate);

export default counter;