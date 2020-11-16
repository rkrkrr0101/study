import React, { Component } from 'react'

export default class Counter extends Component {

        state={
            num:0,
            fnum:0
        }
    render(){
        const{num,fnum}=this.state;



        return (
            <div>
                <h1>{num}</h1>
                <h2>{fnum}</h2>
                <button onClick={()=>{


                    this.setState(
                        {
                            num:num+1
                        },
                        ()=>{
                            console.log('state호출')
                            console.log(this.state)
                        }

                    )


                    


                }
                
                }
                >
                    ++
                </button>
            </div>

        )

    }

}
