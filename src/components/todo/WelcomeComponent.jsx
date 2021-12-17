import React, {Component} from 'react'
import {Link} from 'react-router-dom'
import RegistrationComponent from './RegistrationComponent';

class WelcomeComponent extends Component {
    render() {
        return (
            <>
                <h1>Welcome To JJLZ!</h1>
                <div className="container">    
                Welcome {this.props.match.params.name}. You can manage your todos <Link to="/watchlist">Watch List</Link>.
               
                </div>
            </>
        )        
    }
}
export default WelcomeComponent