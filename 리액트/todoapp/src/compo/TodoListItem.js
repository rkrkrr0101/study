import React from 'react';
import {
    MdCheckBoxOutlineBlank,
    MdCheckBox,
    MdRemoveCircleOutline,
} from 'react-icons/md'
import './TodoListItem.scss'
import Cn from 'classnames'


const TodoListItem = ({Todo,onRemove,onToggle,style}) => {
    const{id,text,checked}=Todo;
    return (
        <div className='TodoListItem-virtualized' style={style}>
            <div className='TodoListItem'>
                <div className={Cn('CheckBox',{checked})} onClick={()=>onToggle(id)}>
                    {checked?<MdCheckBox/>:<MdCheckBoxOutlineBlank/>}  
                    
                    <div className="text">{text}</div>
                </div>
                <div className="remove" onClick={()=>onRemove(id)}  >
                    <MdRemoveCircleOutline/>
                </div>
                
            </div>
        </div>
    );
};

export default React.memo(
    TodoListItem,
    (prevProps,nextProps)=>prevProps.Todo===nextProps.Todo,
    );