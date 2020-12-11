
import { useCallback, useState,useRef } from 'react';
import './App.css';
import Todoinsert from './compo/Todoinsert';
import TodoList from './compo/TodoList';
import Todotem from './compo/Todotem';


function App() {
  const [Todos,setTodos]=useState([
    {
      id:1,
      text:'리액트기초',
      checked:true,

    },
    {
      id:2,
      text:'dlfwjd',
      checked:false,

    },
    {
      id:3,
      text:'zjaxh',
      checked:true,

    },
    {
      id:4,
      text:'adadzjaxh',
      checked:false,

    },
  ])

  const nextid=useRef(5);
  const onInsert=useCallback(
    text => {
      const todo={
        id:nextid.current,
        text,
        checked:false,
      };
      setTodos(Todos.concat(todo));
      nextid.current+=1;
    },
    [Todos],
  );
  const onRemove=useCallback(
    id => {
      setTodos(Todos.filter(todo=>todo.id!==id))
    },
    [Todos],
  )
  const onToggle=useCallback(
    id => {
      setTodos(
        Todos.map(todo=>
            todo.id===id?{...todo,checked:!todo.checked}:todo,
          )
      )
    },
    [Todos],
  )
  return (
    <Todotem>
      <Todoinsert onInsert={onInsert}></Todoinsert>
      <TodoList Todos={Todos} onRemove={onRemove} onToggle={onToggle}/>
    </Todotem>
  );
}

export default App;
