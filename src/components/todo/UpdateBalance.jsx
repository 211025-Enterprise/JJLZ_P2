import React, {Component} from 'react'


class UpdateBalance extends Component {
    constructor(props) {
        super(props)
        this.state = this.initialState
    }

render = () => {
    return (
        <div>
            <h1>User Balance</h1><br/>
            <div>
            <input type="text" name="username"  placeholder='Deposit' onChange={this.handleChange}/>
            <button className="btn btn-success" onClick={() => this.props.history.push("/updateBalance")}>Deposit</button><br />
            <input type="text" name="username"  placeholder='Withdraw' onChange={this.handleChange}/>
            <button className="btn btn-success" onClick={() => this.props.history.push("/updateBalance")}>Withdrwa</button><br />
            </div>
        </div>
    )
   }
}

export default UpdateBalance
  