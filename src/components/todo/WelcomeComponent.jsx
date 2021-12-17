import React, {Component} from 'react'
import {Link} from 'react-router-dom'

class WelcomeComponent extends Component {
    render() {
        return (
            <>
                <h1>Welcome To JJLZ!</h1>
                <div className="container">    
                Welcome {this.props.match.params.name}. You can manage your todos <Link to="/todos">Watch List</Link>.
               
                </div>
            </>
        )        
    }
}
export default WelcomeComponent