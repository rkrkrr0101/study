import React,{useEffect} from 'react';
import styled from 'styled-components'
import NewsItem from './NewsItem'
import Axios from 'axios'
import { useState } from 'react/cjs/react.development';

const NewsListBlock=styled.div`
    box-sizing:border-box;
    paddong-bottom:3rem;
    width:768px;
    margin:0 auto;
    margib-top:2rem;
    @media screen and (max-width:768px){
        width:100%;
        padding-left:1rem;
        padding-right:1rem;
    }

`;




const NewsList = ({category}) => {
    const [articles,setArticles]= useState(null);
    const [loading,setLoading]= useState(false);
    useEffect(() => {
        const fetchData= async()=>{
            setLoading(true)
            try{
                const query=category==='all'?"":`&category=${category}`;
                const response= await Axios.get(`http://newsapi.org/v2/top-headlines?country=kr${query}&apiKey=3753f0a28bad4968a8532d43dafc7b33`)
                setArticles(response.data.articles)
                
            } catch(e){
                console.log(e)
            }
            setLoading(false)
        }
        fetchData();
    }, [category])

    if (loading){
        return<NewsListBlock>대기중</NewsListBlock>
    }
    if(!articles){
        return null
    }

    return (
        <NewsListBlock>
            {articles.map(article=>(
                <NewsItem key={articles.url} article={article}/>
            ))}
        </NewsListBlock>
            
        
    );
};

export default NewsList;