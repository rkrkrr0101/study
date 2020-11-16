import React,{useState} from 'react';

const Iter = () => {
    const [name, setname] = useState([
        {id:1,text:'코끼리'},
        {id:2,text:'코뿔소'},
        {id:3,text:'두더지'},
        {id:4,text:'사슴'}
    ])
    const [inputtext,setinputtext]=useState('')
    const [nextid, setnextid] = useState(5)

    const onChange = e =>setinputtext(e.target.value)
    const onClick= ()=>{
        const nextname=name.concat({
            id:nextid,
            text:inputtext+':'+nextid
        })
        setnextid(nextid+1)
        setname(nextname)
        setinputtext('')

    }
    const onRemove = id =>{
        const nameremove=name.filter(name=>name.id!==id)
        setname(nameremove)
    }

const namelist=name.map(name=><li key={name.id} onDoubleClick={()=>onRemove(name.id)} >{name.text}</li>)
    return (
        <>
            <input value={inputtext} onChange={onChange}></input>
            <button onClick={onClick}>삽입</button>
            <ul>
                {namelist}
                
            </ul>
        </>
        
    );
};

export default Iter;