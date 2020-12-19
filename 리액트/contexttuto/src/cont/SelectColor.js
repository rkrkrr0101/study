import React from 'react';
import { ColorConsumer } from './Color';

const colors=['red','orange','yellow','green','indigo','violet']

const SelectColor = () => {
    return (
        <div>
            <h2>색선택</h2>
            <ColorConsumer>
            {({action})=>(
                <div style={{display:'flex'}}>
                    {colors.map(color=>(
                        <div 
                            key={color}
                            style={{
                                background:color,
                                width:'24px',
                                height:'24px',
                                cursor:'pointer'
                            }}
                            onClick={()=>action.setColor(color)}
                            onContextMenu={e=>{
                                e.preventDefault();
                                action.setSubcolor(color)
                            }}
                        />
                    ))}

                </div>
            )}
            </ColorConsumer>
            <hr/>
        </div>
    );
};



export default SelectColor;