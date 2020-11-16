import React from 'react';
import PropTypes from 'prop-types'

const mycom      = ({name,children,faNum}) => {
    
    return (
        <div>
            네임{'->'} {name} {'<-'}  <br/>
           칠드런{'->'} {children} {'<-'}<br/>
           fanum{'->'} {faNum} {'<-'}
        </div>
    );
};
mycom.defaultProps={
    name:'디폴트'
};
mycom.propTypes={
    name:PropTypes.string,
    faNum:PropTypes.number.isRequired
    
}


export default mycom;




