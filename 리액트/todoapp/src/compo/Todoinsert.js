import React,{useState,useCallback} from 'react';
import {MdAdd}from 'react-icons/md';
import './Todoinsert.scss'

const Todoinsert = ({onInsert}) => {
    const[value,setvalue]=useState('');
    const onChange=useCallback(
        e => {
            setvalue(e.target.value);
        },
        []
    )
    const onsubmit=useCallback(
        e => {
            onInsert(value);
            setvalue('');
            e.preventDefault();
        },
        [onInsert,value],
    )
    return (
        <form className='Todoins' onSubmit={onsubmit}>
            <input 
            placeholder='할일을 입력하세요'
            value={value}
            onChange={onChange}
            />
            <button type="submit">
                <MdAdd></MdAdd>
            </button>
        </form>
    );
};

export default Todoinsert;