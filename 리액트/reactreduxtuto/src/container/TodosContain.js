import React, { useCallback } from 'react';
import { connect,useSelector,useDispatch } from 'react-redux';
import {changeinput,insert,toggle,remove} from '../modules/Todos'
import Todos from '../compo/Todos'
import useActions from '../lib/useActions'
 


const TodosContain = () => {
    const {input,todos}=useSelector(({todos})=>({
        input:todos.input,
        todos:todos.todos
    }) )
    const dispatch=useDispatch()
    const [onChangeInput,onInsert,onToggle,onRemove]=useActions([changeinput,insert,toggle,remove],[])
    return (
        <Todos
        input={input}
        todos={todos}
        onChangeInput={onChangeInput}
        onInsert={onInsert}
        onToggle={onToggle}
        onRemove={onRemove}
        />
    );
};

export default TodosContain;