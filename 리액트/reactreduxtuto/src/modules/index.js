import {combineReducers} from 'redux'
import counter from './Counter'
import todos from './Todos'


const rootReducer=combineReducers({
    counter,todos
})
export default rootReducer;