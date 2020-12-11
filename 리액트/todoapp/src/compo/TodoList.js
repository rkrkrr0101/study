import React from 'react';
import TodoListItem from './TodoListItem';
import './TodoList.scss'

const TodoList = ({Todos,onRemove,onToggle}) => {
    return (
        <div className="TodoList">
            {Todos.map(Todo=>(
                <TodoListItem Todo={Todo} key={Todo.id} onRemove={onRemove} onToggle={onToggle}/>
            ))}
        </div>
    );
};

export default TodoList;