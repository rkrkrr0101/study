import React from 'react';
import WithRouterSam from './withRouterSam';

const data={
    rkrk:{
        name:'rr',
        desc:'abcd'
    },
    qwer:{
        name:'lol',
        desc:'wow'
    },    
}


const Profile = ({match}) => {
    const {username}=match.params;
    const profi=data[username]
    if(!profi){
        return <div>잘못된입력</div>
    }
    return (
        <div>
            <h3>
                {username}({profi.name})
            </h3>
            <p>{profi.desc}</p>
            <WithRouterSam/>
        
        </div>
    );
};

export default Profile;