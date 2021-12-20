import React, {Component} from 'react'
import axios from 'axios'

import AuthenticationService from './AuthenticationService.js'
class UserComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            users : [],
            newBalance:"",
            token: AuthenticationService.getLoggedInUserName(),
        };
    }
    
    handleChange = (e) => {
        this.setState(
            {
                [e.target.name]
                  :e.target.value
            }
        )
    }
componentDidMount=()=>{
this.getUser();
this.updateBalance();
}

getUser = () =>{
    console.log(this.state.token)
    axios.get('http://localhost:8080/users')
    //axios.get(`http://localhost:8080/users/${this.state.username}`)
    .then(response => response.data)
    .then((response) => {
          this.setState({users : response.data})
     })
}
updateBalance = ()=>{
       
    console.log(this.state.userName);
     console.log(this.state.newBalance);
    // axios.put(`http://localhost:8080/users/${this.state.userName}/${this.state.newBalance}`)
    //axios.put(`http://localhost:8080/users/${this.state.newBalance}`)
    // axios.put(`http://localhost:8080/users/${this.state.userName}/${this.state.newBalance}`, this.state.users)
     axios.put(`http://localhost:8080/users/${this.state.token}`,this.state.newBalance)
    .then(response => response.data)
    .then((response) => {
     this.setState({users: response.data})
        console.log(this.state.users)
     })
}

render = ()=> {
    return (
        <div>
            Welcome {this.state.user}. You can manage your Stocks.
             <h1>User Detail</h1>
             <div className="container">
                <table className="table">
                    <thead>
                        <tr>
                            <th>FirstName</th>
                            <th>LastName</th>
                            <th>Balance</th>                   
                        </tr>
                    </thead>
                    <tbody>
                    {
                       
                        this.state.users.map (
                            user =>
                                <tr key={user.userId}>
                                    <td>{user.firstName}</td>
                                    <td>{user.lastName}</td>
                                    <td>{user.balance}</td>
                                     Enter balance: <input type="text" name="newBalance" value={this.state.newBalance} placeholder='Enter balance' onChange={this.handleChange}/>
                              <button className="btn btn-success" onClick={() => this.updateBalance()}>Update Balance</button>
                   {/* <div>{this.state.token}</div> */}
                   {/* <div>{this.state.newBalance}</div>  */}
                                </tr>   
                        )
                        }
                    </tbody>
                </table>
             </div>
        </div>
    )
  }
}

export default UserComponent


