import React, { Component } from 'react'

export default class Event extends Component {
    state={
        message:'',
        user:''
    }

    handlechange=(e)=>{
        this.setState({
            [e.target.name]:e.target.value
        })
    }
    handleclick=()=>{
        alert(this.state.message+':'+this.state.user)
        this.setState({
            message:'',
            user:''
        })
    }
    handlekeypress=(e)=>{
        if (e.key==='Enter'){
            this.handleclick();
           
        }
    }

    render() {
        return (
            <div>
                <h1>이벤트연습</h1>
                <input
                type='text'
                name="user"
                placeholder="가오가이거"
                value={this.state.user}
                onChange={this.handlechange}/>
                <input
                type='text'
                name="message"
                placeholder="갸가가가"
                value={this.state.message}
                onChange={this.handlechange}
                onKeyPress={this.handlekeypress}/>


                <button onClick={this.handleclick} 
                >방출</button>
            </div>
        )
    }
}
