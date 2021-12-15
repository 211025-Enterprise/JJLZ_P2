import React, {Component} from 'react'
//import AuthenticationService from './AuthenticationService.js'

class RegistrationComponent extends Component {
    
    constructor(props) {
        super(props)
        
        this.state = {
            firstName: '',
            lastName: '',
            username: 'zeyad',
            password: '',
            hasLoginFailed: false,
            showSuccessMessage: false
        }

        this.handleChange = this.handleChange.bind(this)
         this.registerClicked = this.registerClicked.bind(this)
    }

    handleChange(event) {
        //console.log(this.state);
        this.setState(
            {
                [event.target.name]
                  :event.target.value
            }
        )
    }

    registerClicked() {
    
        if(this.state.username ==='zeyad' && this.state.password ==='zeyad'){
            this.props.history.push(`/login/${this.state.firstName}`)
            this.setState({showSuccessMessage:true})
            this.setState({hasLoginFailed:false})
        }
        else {
           this.setState({showSuccessMessage:false})
            this.setState({hasLoginFailed:true})
       }
    }

    render() {
        return (
            <div>
                <h1>Registration</h1>
                <div className="container">
                    {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
                    {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                    {this.state.showSuccessMessage && <div>Registered Sucessful</div>}
        
                    First Name: <input type="text" name="firstName" value={this.state.firstName} onChange={this.handleChange}/><br/>
                    Last Name: <input type="text" name="lastName" value={this.state.lastName}  onChange={this.handleChange}/><br/>
                    UserName: <input type="text" name="username" value={this.state.username} onChange={this.handleChange}/><br/>
                    Password: <input type="text" name="password" value={this.state.password}  onChange={this.handleChange}/><br/><br/>
                    <button className="btn btn-success " onClick={this.registerClicked}>Register</button>
                </div>
            </div>
        )
    }
}

export default RegistrationComponent