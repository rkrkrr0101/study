import React, { Component } from 'react'
import './Valid.css'

export default class Vaild extends Component {
    state={
        password:'',
        click:false,
        validate:false


    
    
    }
    handleChange=(e)=>{
        
        this.setState({
            password: e.target.value

            
        });
    }


    handlebuttonclick=()=>{
        this.setState({
            click:true,
            validate:this.state.password==='0000'
        });
        this.cocoa.focus()
    }
    render() {
        return (
            <div>
                <input
                    type='password'
                    value={this.state.password}
                    onChange={this.handleChange}
                    className={this.state.click?(this.state.validate?'success':'fail' ):''}
                    ref={(ref)=>this.cocoa=ref}
                    />
                <button onClick={this.handlebuttonclick}>검증</button>

            </div>
        )
    }
}
