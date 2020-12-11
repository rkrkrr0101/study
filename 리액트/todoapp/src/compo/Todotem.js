import React from 'react';
import './Todotem.scss'

const Todotem = ({children}) => {
    return (
        <div className="Todotem" >
            
            <div className="app-title">일정관리</div>
            <div className="content">{children }  </div>
            
        </div>
    );
};

export default Todotem;