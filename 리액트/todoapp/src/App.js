
import { useCallback, useState,useRef, useReducer } from 'react';
import './App.css';
import Todoinsert from './compo/Todoinsert';
import TodoList from './compo/TodoList';
import Todotem from './compo/Todotem';

function createbulk() {
  const array=[];
  for(let i=1;i<=2500;i++){
    array.push({
      id:i,
      text:`할일${i}`,
      checked:false,
    });
  }
  return array;
}
function todoReducers(Todos,action) {
  switch(action.type){
      case 'insert':
        return Todos.concat(action.Todo)
      case 'remove':
        return Todos.filter(todo=>todo.id!==action.id)
      case 'toggle':
        return Todos.map(todo=>todo.id===action.id?{...todo,checked:!todo.checked}:todo,)
      default:
        return Todos

  }
  
}


function App() {
  const [Todos,dispatch]=useReducer(todoReducers,undefined,createbulk);

  const nextid=useRef(2501);
  const onInsert=useCallback(
    text => {
      const todo={
        id:nextid.current,
        text,
        checked:false,
      };
      dispatch({type:'insert',todo});
      nextid.current+=1;
    },
    [],
  );
  const onRemove=useCallback(
    id => {
      dispatch({type:'remove',id})
    },
    [],
  )
  const onToggle=useCallback(
    id => {
      dispatch({type:'toggle',id})
    },
    [],
  )
  return (
    <Todotem>
      <Todoinsert onInsert={onInsert}></Todoinsert>
      <TodoList Todos={Todos} onRemove={onRemove} onToggle={onToggle}/>
    </Todotem>
  );
}

export default App;
