import React from 'react';
import {Link} from 'react-router-dom'
const menu = () => {
    return (
        <ul>
            <li>
                <Link to='/red'>red</Link>
            </li>
            <li>
                 <Link to='/blue'>blue</Link>
            </li>
        </ul>
    );
};

export default menu;