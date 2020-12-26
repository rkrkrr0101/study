import React from 'react';

const Sample = ({loadingPost,LoadingUsers,post,users}) => {
    return (
        <div>
            <section>
                <h1>포스트</h1>
                {loadingPost&&'로딩중'}
                {!loadingPost&&post&&(
                    <div>
                        <h3>{post.title}</h3>
                        <h3>{post.body}</h3>
                    </div>
                )}
            </section>
            <hr/>
            <section>
                <h1>사용자목록</h1>
                {LoadingUsers&&'로딩중'}
                {!LoadingUsers&&users&&(
                    <ul>
                        {users.map(user=>(
                            <li key={user.id}>
                                {user.username}({user.email})
                            </li>
                        ))}
                    </ul>
                )}
            </section>
        </div>
    );
};

export default Sample;