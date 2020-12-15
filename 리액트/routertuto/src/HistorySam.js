import React,{Component,useEffect} from 'react';

class HistorySam extends Component {
    handleGoBack=()=>{
        this.props.history.goBack();
    }
     handleGoHome=()=>{
        this.props.history.push('/');
    };
    componentDidMount() {
        this.unblock=this.props.history.block('정말 나가?');
          
    };
    componentWillUnmount(){
        if (this.unblock){
            this.unblock();
        }
    }
    render(){        
        return (
            <div>
                <div>
                    <button onClick={this.handleGoBack}>뒤로</button>
                    <button onClick={this.handleGoHome}>홈으로</button>
                </div>
            </div>
        );
    }
};

export default HistorySam;