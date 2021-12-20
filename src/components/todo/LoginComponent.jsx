import React, {Component} from 'react'
import AuthenticationService from './AuthenticationService.js'
import axios from 'axios'
class LoginComponent extends Component {
    
    constructor(props) {
        super(props)
        this.state = this.initialState
    }

    handleChange = (e) => {
        this.setState(
            {
                [e.target.name]
                  :e.target.value
            }
        )
    }

    handleSubmit= (e)=> {
        // Process submit from this.state
        e.preventDefault(); // Need to stop DOM from generating a POST
        }
    initialState={
            username: "",
            password: ""
       }
    login = ()=> {
       this.props.history.push(`/welcome/${this.state.username}`)
        const loggedUser = {
            username: this.state.username,
            password: this.state.password
           }  
           axios.post('http://localhost:8080/logged', loggedUser) 
          .then((response) => {
              if(response.data != null)
              AuthenticationService.registerSuccessfulLogin(response.data)
                this.setState(this.initialState)     
               alert("You have logged in Successfully")                
           }).catch(() => 
           alert("User not exsit"),  
             this.refreshPage()
           )                  
    }

      refreshPage = () => {
         window.location.reload(false);
       }
    
    render = () => {
        return (
            <div>
                <h1>Login</h1>
                <div className="container">
                <form onSubmit={this.handleSubmit}>
                    UserName: <input type="text" name="username" value={this.state.username} placeholder='Enter username' onChange={this.handleChange}/>
                    Password: <input type="password" name="password" value={this.state.password} placeholder='Enter password' onChange={this.handleChange}/>
                    <button className="btn btn-success " onClick={this.login} >Login</button><br />
                    </form>
                </div>
            </div>
        )
    }
}

    
export default LoginComponent