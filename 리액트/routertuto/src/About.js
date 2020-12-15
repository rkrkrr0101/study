import React from 'react';
import qs from 'qs'

const About = ({location}) => {
    const query=qs.parse(location.search,{
        ignoreQueryPrefix:true
    })
    const showDetail=query.detail==='true';

    return (
        <div>
            <h1>About</h1>
            <p>리액트튜토리얼 </p>
            {showDetail&&<p>detail true</p>}
        </div>
    );
};

export default About;