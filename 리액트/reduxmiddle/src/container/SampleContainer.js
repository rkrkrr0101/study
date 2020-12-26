import React from 'react';
import { connect } from 'react-redux';
import Sample from '../compo/Sample'
import loading from '../module/loading';
import {getPost,getUsers} from '../module/smaple'

const {useEffect}=React
const SampleContainer = ({
    getPost,
    getUsers,
    post,
    users,
    loadingPost,
    loadingUsers
}) => {
    useEffect(() => {
        getPost(15,'ad');
        getUsers(12,'sd');
    }, [getPost,getUsers])
    return (
        <Sample
        post={post}
        users={users}
        loadingPost={loadingPost}
        loadingUsers={loadingUsers}
        ></Sample>
    );
};

export default connect(
    ({sample})=>({
        post:sample.post,
        users:sample.users,
        loadingPost:loading['sample/GET_POST'],
        loadingUsers:loading['sample/GET_USERS']
    }),
    {
        getPost,
        getUsers
    }



) (SampleContainer);