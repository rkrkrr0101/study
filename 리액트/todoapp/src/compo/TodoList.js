import React, { useCallback } from 'react';
import TodoListItem from './TodoListItem';
import {List} from 'react-virtualized'
import './TodoList.scss'
//495 57
const TodoList = ({Todos,onRemove,onToggle}) => {
    const rowrenderer=useCallback(
        ({index,key,style})=>{
            const Todo=Todos[index];
            return(
                <TodoListItem
                 Todo={Todo}
                 key={key}
                 onRemove={onRemove}
                 onToggle={onToggle}
                 style={style}
                 />
            )
        },[onRemove,onToggle,Todos]

    )
    return (
        <List
         className="TodoList"
         width={495}
         height={496}
         rowCount={Todos.length}
         rowHeight={57}
         rowRenderer={rowrenderer}
         list={Todos}
         style={{outline:'none'}}
         />
    );
};

export default React.memo (TodoList);