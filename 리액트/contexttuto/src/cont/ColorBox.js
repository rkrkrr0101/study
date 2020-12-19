import React, { useContext } from 'react';
import ColorContext, { ColorConsumer } from '../cont/Color'

const ColorBox = () => {
    const {state}=useContext(ColorContext)//이게 전역변수지
    return (

            <>
                <div
                    style={{
                        width:'64px',
                        height:'64px',
                        background:state.color
                        
                    }}
                />
                <div
                style={{
                    width:'64px',
                    height:'64px',
                    background:state.subcolor
                    
                }}
            />
        </>


    );
};

export default ColorBox;