import React from 'react';
import Categories from './compo/Categories';
import NewsList from './compo/NewsList';

const newsPage = ({match}) => {
    const category=match.params.category||'all';
    return (
        <div>
            <Categories/>
            <NewsList category={category}></NewsList>
        </div>
    );
};

export default newsPage;